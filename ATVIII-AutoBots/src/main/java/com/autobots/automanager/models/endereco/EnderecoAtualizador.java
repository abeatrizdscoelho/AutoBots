package com.autobots.automanager.models.endereco;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entities.Endereco;
import com.autobots.automanager.models.StringVerificadorNulo;

@Component
public class EnderecoAtualizador {

    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizar(Endereco endereco, Endereco atualizacao) {

        if (!verificador.verificar(atualizacao.getEstado())) {
            endereco.setEstado(atualizacao.getEstado());
        }
        if (!verificador.verificar(atualizacao.getCidade())) {
            endereco.setCidade(atualizacao.getCidade());
        }
        if (!verificador.verificar(atualizacao.getBairro())) {
            endereco.setBairro(atualizacao.getBairro());
        }
        if (!verificador.verificar(atualizacao.getRua())) {
            endereco.setRua(atualizacao.getRua());
        }
        if (!verificador.verificar(atualizacao.getNumero())) {
            endereco.setNumero(atualizacao.getNumero());
        }
        if (!verificador.verificar(atualizacao.getCodigoPostal())) {
            endereco.setCodigoPostal(atualizacao.getCodigoPostal());
        }
        if (!verificador.verificar(atualizacao.getInformacoesAdicionais())) {
            endereco.setInformacoesAdicionais(atualizacao.getInformacoesAdicionais());
        }
    }
}
