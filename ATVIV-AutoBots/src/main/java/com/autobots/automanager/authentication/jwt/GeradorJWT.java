package com.autobots.automanager.authentication.jwt;

import java.sql.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

class GeradorJWT {
    private String assinatura;
    private Date expiracao;

    public GeradorJWT(String assinatura, long duracao) {
        this.assinatura = assinatura;
        this.expiracao = new Date(System.currentTimeMillis() + duracao);
    }

    public String gerarJWT(String nomeUsuario) {
        SecretKey key = Keys.hmacShaKeyFor(assinatura.getBytes());
        return Jwts.builder()
                .setSubject(nomeUsuario)
                .setExpiration(this.expiracao)
                .signWith(key)
                .compact();
    }
}
