package com.autobots.automanager.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entities.Empresa;
import com.autobots.automanager.entities.Usuario;
import com.autobots.automanager.entities.Venda;
import com.autobots.automanager.enumerations.PerfilUsuario;
import com.autobots.automanager.repositories.UsuarioRepository;
import com.autobots.automanager.repositories.VendaRepository;
import com.autobots.automanager.utils.AuthUtil;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VendaService {

    @Autowired
    private AuthUtil authUtil;
    
    @Autowired
    private VendaRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Venda> listar() {
        return repository.findAll();
    }

    public List<Venda> listarPorUsuario(String nomeUsuario) {
        Usuario usuarioLogado = usuarioRepository.findByCredencialNomeUsuario(nomeUsuario) 
            .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado."));

        boolean isCliente = usuarioLogado.getPerfis().contains(PerfilUsuario.ROLE_CLIENTE);
        boolean isVendedor = usuarioLogado.getPerfis().contains(PerfilUsuario.ROLE_VENDEDOR);

        // Cliente vê apenas as vendas em que foi consumidor.
        if (isCliente) {
            return repository.findAll().stream()
                .filter(v -> v.getCliente() != null && v.getCliente().getId().equals(usuarioLogado.getId()))
                .collect(Collectors.toList());
        }

        // Administrador e gerente podem ver tudo, vendedor vê só suas vendas.
        if (isVendedor) {
            return repository.findAll().stream()
                .filter(v -> v.getVendedor() != null && v.getVendedor().getId().equals(usuarioLogado.getId()))
                .collect(Collectors.toList());
        }
        
        return repository.findAll();
    }

    public Optional<Venda> buscarPorIdAutorizado(Long id) {
        Optional<Venda> vendaOptional = repository.findById(id);
        if (vendaOptional.isEmpty()) {
            return Optional.empty();
        }

        Venda venda = vendaOptional.get();
        Usuario usuarioLogado = authUtil.getUsuarioLogado();

        // Clientes só podem ver as vendas onde são o consumidor.
        if (authUtil.isCliente()) {
            if (venda.getCliente() == null || !venda.getCliente().getId().equals(usuarioLogado.getId())) {
                throw new AccessDeniedException("Cliente só pode visualizar as suas próprias vendas.");
            }
            return vendaOptional;
        }
        
        // Vendedores só podem ver/atualizar/deletar as vendas que ele próprio fez.
        if (authUtil.isVendedor()) {
            if (venda.getVendedor() == null || !venda.getVendedor().getId().equals(usuarioLogado.getId())) {
                throw new AccessDeniedException("Vendedor só pode visualizar/atualizar/deletar suas próprias vendas.");
            }
            return vendaOptional;
        }

        return repository.findById(id);
    }

    public Venda salvar(Venda venda) {
        Usuario usuarioLogado = authUtil.getUsuarioLogado();

        // Só vendedores, gerentes ou admins podem criar vendas.
        if (!(authUtil.isVendedor() || authUtil.isGerente() || authUtil.isAdmin())) {
            throw new AccessDeniedException("Cliente não tem permissão para criar vendas.");
        }

        // Se for vendedor, só pode criar vendas associadas a ele.
        if (authUtil.isVendedor()) {
            venda.setVendedor(usuarioLogado);
        }

        Empresa empresa = authUtil.getEmpresaLogada();
        venda.setEmpresa(empresa);
        empresa.getVendas().add(venda);
        
        return repository.save(venda);
    }

    public void deletar(Long id) {
        Venda venda = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Venda não encontrada."));

        Usuario usuarioLogado = authUtil.getUsuarioLogado();

        if (authUtil.isVendedor() && !venda.getVendedor().getId().equals(usuarioLogado.getId())) {
            throw new AccessDeniedException("Vendedor só pode deletar suas próprias vendas.");
        }

        if (!(authUtil.isVendedor() || authUtil.isGerente() || authUtil.isAdmin())) {
            throw new AccessDeniedException("Cliente não tem permissão para deletar vendas.");
        }
        repository.deleteById(id);
    }
}
