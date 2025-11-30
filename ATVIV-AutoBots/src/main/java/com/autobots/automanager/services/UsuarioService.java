package com.autobots.automanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entities.Empresa;
import com.autobots.automanager.entities.Usuario;
import com.autobots.automanager.enumerations.PerfilUsuario;
import com.autobots.automanager.repositories.EmpresaRepository;
import com.autobots.automanager.repositories.UsuarioRepository;
import com.autobots.automanager.utils.AuthUtil;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {

    @Autowired
    private AuthUtil authUtil;
    
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired 
    private BCryptPasswordEncoder passwordEncoder;

    public List<Usuario> listarAutorizado() {
        Usuario usuarioLogado = authUtil.getUsuarioLogado();

        if (authUtil.isCliente()) {
            return List.of(usuarioLogado);
        }

        return repository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Optional<Usuario> buscarPorIdAutorizado(Long id) {
        Optional<Usuario> usuarioOptional = repository.findById(id);
        if (usuarioOptional.isEmpty()) {
            return Optional.empty();
        }

        Usuario usuarioLogado = authUtil.getUsuarioLogado();

        if (authUtil.isCliente() && !usuarioLogado.getId().equals(id)) {
            throw new AccessDeniedException("Cliente só pode visualizar o seu próprio cadastro.");
        }

        return repository.findById(id);
    }

    public Usuario salvar(Usuario usuario) {
        Empresa empresaLogada = authUtil.getEmpresaLogada();

        if (usuario.getCredencial() != null && usuario.getCredencial().getSenha() != null) {
            String senhaCodificada = passwordEncoder.encode(usuario.getCredencial().getSenha());
            usuario.getCredencial().setSenha(senhaCodificada);
        }

        usuario.setEmpresa(empresaLogada);
            
        boolean criandoAdmin = usuario.getPerfis().contains(PerfilUsuario.ROLE_ADMIN);
        boolean criandoCliente = usuario.getPerfis().contains(PerfilUsuario.ROLE_CLIENTE);

        // Gerentes não podem criar/editar administradores.
        if (authUtil.isGerente() && criandoAdmin) {
            throw new AccessDeniedException("Gerente não tem permissão para criar ou editar administradores.");
        }

        // Vendedores só podem criar/editar clientes.
        if (authUtil.isVendedor() && !criandoCliente) {
            throw new AccessDeniedException("Vendedor só pode criar ou editar usuários com perfil CLIENTE.");
        }

        return repository.save(usuario);
    }

    public void deletar(Long id) {
        Optional<Usuario> usuarioOptional = repository.findById(id);

        if (usuarioOptional.isPresent()) {
            Usuario usuarioDeletado = usuarioOptional.get();
            boolean deletandoAdmin = usuarioDeletado.getPerfis().contains(PerfilUsuario.ROLE_ADMIN);
            boolean deletandoCliente = usuarioDeletado.getPerfis().contains(PerfilUsuario.ROLE_CLIENTE);

            // Gerentes não podem deletar administradores.
            if (authUtil.isGerente() && deletandoAdmin) {
                throw new AccessDeniedException("Gerente não tem permissão para deletar administradores.");
            }

            // Vendedores só podem deletar clientes.
            if (authUtil.isVendedor() && !deletandoCliente) {
                throw new AccessDeniedException("Vendedor só pode deletar usuários com perfil CLIENTE.");
            }

        }
        repository.deleteById(id);
    }

    public Usuario registrarUsuario(Long empresaId, Usuario novo) {
        if (novo.getCredencial() != null && novo.getCredencial().getSenha() != null) {
            String senhaCodificada = passwordEncoder.encode(novo.getCredencial().getSenha());
            novo.getCredencial().setSenha(senhaCodificada);
        }

        Empresa empresa = empresaRepository.findById(empresaId)
            .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada."));
        novo.setEmpresa(empresa);      
        return repository.save(novo); 
    }
}
