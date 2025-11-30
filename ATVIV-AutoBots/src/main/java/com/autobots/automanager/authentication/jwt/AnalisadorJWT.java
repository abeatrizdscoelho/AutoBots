package com.autobots.automanager.authentication.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

class AnalisadorJWT {
    private String assinatura;
    private String jwt;

    public AnalisadorJWT(String assinatura, String jwt) {
        this.assinatura = assinatura;
        this.jwt = jwt;
    }

    @SuppressWarnings("deprecation")
    public Claims obterReividicacoes() {
        try {
            return Jwts.parser().setSigningKey(assinatura.getBytes()).parseClaimsJws(jwt).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public String obterNomeUsuario(Claims reivindicacoes) {
        if (reivindicacoes != null) {
            String nomeUsuario = reivindicacoes.getSubject();
            return nomeUsuario;
        }
        return null;
    }
}
