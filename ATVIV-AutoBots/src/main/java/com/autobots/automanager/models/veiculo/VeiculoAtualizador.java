package com.autobots.automanager.models.veiculo;

import org.springframework.stereotype.Component;

import com.autobots.automanager.entities.Veiculo;
import com.autobots.automanager.models.StringVerificadorNulo;

@Component
public class VeiculoAtualizador {
    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizar(Veiculo veiculo, Veiculo atualizacao) {

        if (!verificador.verificar(atualizacao.getPlaca())) {
            veiculo.setPlaca(atualizacao.getPlaca());
        }

        if (!verificador.verificar(atualizacao.getModelo())) {
            veiculo.setModelo(atualizacao.getModelo());
        }

        if (atualizacao.getTipo() != null) {
            veiculo.setTipo(atualizacao.getTipo());
        }

        if (atualizacao.getProprietario() != null) {
            veiculo.setProprietario(atualizacao.getProprietario());
        }

        if (atualizacao.getVendas() != null && !atualizacao.getVendas().isEmpty()) {
            veiculo.setVendas(atualizacao.getVendas());
        }
    }
}
