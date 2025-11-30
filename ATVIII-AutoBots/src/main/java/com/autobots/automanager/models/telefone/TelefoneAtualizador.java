package com.autobots.automanager.models.telefone;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entities.Telefone;
import com.autobots.automanager.models.StringVerificadorNulo;

@Component
public class TelefoneAtualizador {

    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizar(Telefone telefone, Telefone atualizacao) {

        if (!verificador.verificar(atualizacao.getDdd())) {
            telefone.setDdd(atualizacao.getDdd());
        }
        if (!verificador.verificar(atualizacao.getNumero())) {
            telefone.setNumero(atualizacao.getNumero());
        }
    }
}
