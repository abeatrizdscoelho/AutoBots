package com.autobots.automanager.models.documento;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entities.Documento;
import com.autobots.automanager.models.StringVerificadorNulo;

@Component
public class DocumentoAtualizador {

    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizar(Documento documento, Documento atualizacao) {

        if (atualizacao.getTipo() != null) {
            documento.setTipo(atualizacao.getTipo());
        }

        if (atualizacao.getDataEmissao() != null) {
            documento.setDataEmissao(atualizacao.getDataEmissao());
        }

        if (!verificador.verificar(atualizacao.getNumero())) {
            documento.setNumero(atualizacao.getNumero());
        }
    }
}
