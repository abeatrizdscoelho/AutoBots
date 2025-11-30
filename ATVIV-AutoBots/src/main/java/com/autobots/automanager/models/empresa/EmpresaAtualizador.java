package com.autobots.automanager.models.empresa;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entities.Empresa;
import com.autobots.automanager.models.StringVerificadorNulo;

@Component
public class EmpresaAtualizador {
    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizar(Empresa empresa, Empresa atualizacao) {

        if (!verificador.verificar(atualizacao.getRazaoSocial())) {
            empresa.setRazaoSocial(atualizacao.getRazaoSocial());
        }

        if (!verificador.verificar(atualizacao.getNomeFantasia())) {
            empresa.setNomeFantasia(atualizacao.getNomeFantasia());
        }

        if (atualizacao.getCadastro() != null) {
            empresa.setCadastro(atualizacao.getCadastro());
        }

        if (atualizacao.getEndereco() != null) {
            empresa.setEndereco(atualizacao.getEndereco());
        }

        if (atualizacao.getTelefones() != null && !atualizacao.getTelefones().isEmpty()) {
            empresa.setTelefones(atualizacao.getTelefones());
        }

        if (atualizacao.getUsuarios() != null && !atualizacao.getUsuarios().isEmpty()) {
            empresa.setUsuarios(atualizacao.getUsuarios());
        }

        if (atualizacao.getMercadorias() != null && !atualizacao.getMercadorias().isEmpty()) {
            empresa.setMercadorias(atualizacao.getMercadorias());
        }

        if (atualizacao.getServicos() != null && !atualizacao.getServicos().isEmpty()) {
            empresa.setServicos(atualizacao.getServicos());
        }

        if (atualizacao.getVendas() != null && !atualizacao.getVendas().isEmpty()) {
            empresa.setVendas(atualizacao.getVendas());
        }
    }
}
