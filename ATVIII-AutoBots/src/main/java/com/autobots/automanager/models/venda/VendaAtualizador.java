package com.autobots.automanager.models.venda;

import java.util.HashSet;

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
        
        if (atualizacao.getCliente() != null) {
            venda.setCliente(atualizacao.getCliente());
        }

        if (atualizacao.getFuncionario() != null) {
            venda.setFuncionario(atualizacao.getFuncionario());
        }

        if (atualizacao.getVeiculo() != null) {
            venda.setVeiculo(atualizacao.getVeiculo());
        }

        if (atualizacao.getMercadorias() != null) {
            venda.setMercadorias(atualizacao.getMercadorias());
        } else if (venda.getMercadorias() == null) {
             venda.setMercadorias(new HashSet<>());
        }

        if (atualizacao.getServicos() != null) {
            venda.setServicos(atualizacao.getServicos());
        } else if (venda.getServicos() == null) {
            venda.setServicos(new HashSet<>());
        }
    }
}
