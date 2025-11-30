package com.autobots.automanager.models.venda;

import org.springframework.stereotype.Component;

import com.autobots.automanager.dtos.VendaDTO;
import com.autobots.automanager.entities.Venda;
import com.autobots.automanager.models.StringVerificadorNulo;

@Component
public class VendaAtualizador {

    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizar(Venda venda, VendaDTO atualizacao) {
        
        if (!verificador.verificar(atualizacao.getIdentificacao())) {
            venda.setIdentificacao(atualizacao.getIdentificacao());
        }

        if (atualizacao.getCadastro() != null) {
            venda.setCadastro(atualizacao.getCadastro());
        }
    }
}
