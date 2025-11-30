package com.autobots.automanager.models.usuario;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entities.Usuario;
import com.autobots.automanager.models.StringVerificadorNulo;

@Component
public class UsuarioAtualizador {

    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizar(Usuario usuario, Usuario atualizacao) {

        if (!verificador.verificar(atualizacao.getNome())) {
            usuario.setNome(atualizacao.getNome());
        }

        if (!verificador.verificar(atualizacao.getNomeSocial())) {
            usuario.setNomeSocial(atualizacao.getNomeSocial());
        }

        if (atualizacao.getPerfis() != null && !atualizacao.getPerfis().isEmpty()) {
            usuario.setPerfis(atualizacao.getPerfis());
        }

        if (atualizacao.getTelefones() != null && !atualizacao.getTelefones().isEmpty()) {
            usuario.setTelefones(atualizacao.getTelefones());
        }

        if (atualizacao.getEndereco() != null) {
            usuario.setEndereco(atualizacao.getEndereco());
        }

        if (atualizacao.getDocumentos() != null && !atualizacao.getDocumentos().isEmpty()) {
            usuario.setDocumentos(atualizacao.getDocumentos());
        }

        if (atualizacao.getEmails() != null && !atualizacao.getEmails().isEmpty()) {
            usuario.setEmails(atualizacao.getEmails());
        }

        if (atualizacao.getCredenciais() != null && !atualizacao.getCredenciais().isEmpty()) {
            usuario.setCredenciais(atualizacao.getCredenciais());
        }

        if (atualizacao.getMercadorias() != null && !atualizacao.getMercadorias().isEmpty()) {
            usuario.setMercadorias(atualizacao.getMercadorias());
        }

        if (atualizacao.getVendas() != null && !atualizacao.getVendas().isEmpty()) {
            usuario.setVendas(atualizacao.getVendas());
        }

        if (atualizacao.getVeiculos() != null && !atualizacao.getVeiculos().isEmpty()) {
            usuario.setVeiculos(atualizacao.getVeiculos());
        }

        if (atualizacao.getEmpresa() != null) {
            usuario.setEmpresa(atualizacao.getEmpresa());
        }

    }
}
