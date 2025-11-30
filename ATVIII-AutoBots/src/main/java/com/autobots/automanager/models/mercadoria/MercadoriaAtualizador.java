package com.autobots.automanager.models.mercadoria;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entities.Mercadoria;
import com.autobots.automanager.models.StringVerificadorNulo;

@Component
public class MercadoriaAtualizador {
    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizar(Mercadoria mercadoria, Mercadoria atualizacao) {

        if (!verificador.verificar(atualizacao.getNome())) {
            mercadoria.setNome(atualizacao.getNome());
        }

        if (!verificador.verificar(atualizacao.getDescricao())) {
            mercadoria.setDescricao(atualizacao.getDescricao());
        }

        if (atualizacao.getCadastro() != null) {
            mercadoria.setCadastro(atualizacao.getCadastro());
        }

        if (atualizacao.getFabricacao() != null) {
            mercadoria.setFabricacao(atualizacao.getFabricacao());
        }

        if (atualizacao.getValidade() != null) {
            mercadoria.setValidade(atualizacao.getValidade());
        }

        if (atualizacao.getQuantidade() != 0) {
            mercadoria.setQuantidade(atualizacao.getQuantidade());
        }

        if (atualizacao.getValor() != 0) {
            mercadoria.setValor(atualizacao.getValor());
        }
    }
}
