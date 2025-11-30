package com.autobots.automanager.authentication.filters;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.autobots.automanager.authentication.jwt.ProvedorJWT;

class AutenticadorJWT {
    private String jwt;
    private ProvedorJWT provedorJWT;
    private UserDetailsService service;

    public AutenticadorJWT(String jwt, ProvedorJWT provedorJWT, UserDetailsService service) {
        this.jwt = jwt;
        this.provedorJWT = provedorJWT;
        this.service = service;
    }

    public UsernamePasswordAuthenticationToken obterAutenticacao() {
        if (provedorJWT.validarJWT(jwt)) {
            String nomeUsuario = provedorJWT.obterNomeUsuario(jwt);
            UserDetails usuario = service.loadUserByUsername(nomeUsuario);
            return new UsernamePasswordAuthenticationToken(usuario, usuario.getPassword(), usuario.getAuthorities());
        }
        return null;
    }
}
