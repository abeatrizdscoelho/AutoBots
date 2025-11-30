package com.autobots.automanager.models.servico;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entities.Servico;
import com.autobots.automanager.models.StringVerificadorNulo;

@Component
public class ServicoAtualizador {
    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizar(Servico servico, Servico atualizacao) {

        if (!verificador.verificar(atualizacao.getNome())) {
            servico.setNome(atualizacao.getNome());
        }

        if (atualizacao.getValor() != 0) {
            servico.setValor(atualizacao.getValor());
        }

        if (!verificador.verificar(atualizacao.getDescricao())) {
            servico.setDescricao(atualizacao.getDescricao());
        }
    }
}
