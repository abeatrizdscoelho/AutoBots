package com.autobots.automanager.models.email;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entities.Email;
import com.autobots.automanager.models.StringVerificadorNulo;

@Component
public class EmailAtualizador {

    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizar(Email email, Email atualizacao) {
        if (!verificador.verificar(atualizacao.getEndereco())) {
            email.setEndereco(atualizacao.getEndereco());
        }
    }
}
