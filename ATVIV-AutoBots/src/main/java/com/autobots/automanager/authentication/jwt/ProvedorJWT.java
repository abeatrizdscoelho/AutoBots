package com.autobots.automanager.authentication.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;

@Component
public class ProvedorJWT {

    @Value("${jwt.secret}")
    private String assinatura;

    @Value("${jwt.expiration}")
    private Long duracao;

    private GeradorJWT gerador;
    private AnalisadorJWT analisador;
    private ValidadorJWT validador;

    public String proverJWT(String nomeUsuario) {
        gerador = new GeradorJWT(assinatura, duracao);
        String token = gerador.gerarJWT(nomeUsuario);
        return token;
    }

    public boolean validarJWT(String jwt) {
        try {
            analisador = new AnalisadorJWT(assinatura, jwt);
            validador = new ValidadorJWT();
            boolean valido = validador.validar(analisador.obterReividicacoes());
            return valido;
        } catch (Exception e) {
            return false;
        }
    }

    public String obterNomeUsuario(String jwt) {
        try {
            analisador = new AnalisadorJWT(assinatura, jwt);
            Claims reivindicacoes = analisador.obterReividicacoes();
            String nome = analisador.obterNomeUsuario(reivindicacoes);
            return nome;
        } catch (Exception e) {
            return null;
        }
    }
}
