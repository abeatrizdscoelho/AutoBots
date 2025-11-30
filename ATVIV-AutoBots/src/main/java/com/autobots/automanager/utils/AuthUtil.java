package com.autobots.automanager.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.entities.Empresa;
import com.autobots.automanager.entities.Usuario;
import com.autobots.automanager.enumerations.PerfilUsuario;
import com.autobots.automanager.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Component
public class AuthUtil {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario getUsuarioLogado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String nomeUsuario = auth.getName();

        return usuarioRepository.findByCredencialNomeUsuario(nomeUsuario)
            .orElseThrow(() -> new EntityNotFoundException("Usuário logado não encontrado."));
    }

    public Empresa getEmpresaLogada() {
        Usuario usuario = getUsuarioLogado();
        if (usuario.getEmpresa() == null) {
            throw new EntityNotFoundException("Usuário logado não está vinculado a nenhuma empresa.");
        }
        return usuario.getEmpresa();
    }

    public boolean isAdmin() {
        return getUsuarioLogado().getPerfis().contains(PerfilUsuario.ROLE_ADMIN);
    }

    public boolean isGerente() {
        return getUsuarioLogado().getPerfis().contains(PerfilUsuario.ROLE_GERENTE);
    }

    public boolean isVendedor() {
        return getUsuarioLogado().getPerfis().contains(PerfilUsuario.ROLE_VENDEDOR);
    }

    public boolean isCliente() {
        return getUsuarioLogado().getPerfis().contains(PerfilUsuario.ROLE_CLIENTE);
    }
}
