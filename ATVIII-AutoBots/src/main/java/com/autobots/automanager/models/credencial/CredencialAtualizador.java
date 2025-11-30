package com.autobots.automanager.models.credencial;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entities.CredencialCodigoBarra;
import com.autobots.automanager.entities.CredencialUsuarioSenha;
import com.autobots.automanager.models.StringVerificadorNulo;

@Component
public class CredencialAtualizador {

    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizar(CredencialUsuarioSenha original, CredencialUsuarioSenha atualizacao) {
        if (!verificador.verificar(atualizacao.getNomeUsuario())) {
            original.setNomeUsuario(atualizacao.getNomeUsuario());
        }

        if (!verificador.verificar(atualizacao.getSenha())) {
            original.setSenha(atualizacao.getSenha());
        }

        if (atualizacao.getUltimoAcesso() != null) {
            original.setUltimoAcesso(atualizacao.getUltimoAcesso());
        }

        original.setInativo(atualizacao.isInativo());
    }

    public void atualizar(CredencialCodigoBarra original, CredencialCodigoBarra atualizacao) {
        if (atualizacao.getCodigo() != 0) {
            original.setCodigo(atualizacao.getCodigo());
        }

        if (atualizacao.getUltimoAcesso() != null) {
            original.setUltimoAcesso(atualizacao.getUltimoAcesso());
        }

        original.setInativo(atualizacao.isInativo());
    }
}
