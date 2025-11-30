package com.autobots.automanager.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.autobots.automanager.dtos.VeiculoDTO;
import com.autobots.automanager.entities.Veiculo;

@Component
public class VeiculoConverter {

    public VeiculoDTO converterParaDTO(Veiculo veiculo) {
        VeiculoDTO dto = new VeiculoDTO();
        dto.setId(veiculo.getId());
        dto.setTipo(veiculo.getTipo());
        dto.setModelo(veiculo.getModelo());
        dto.setPlaca(veiculo.getPlaca());
        dto.setProprietario(veiculo.getProprietario());
        dto.setVendas(veiculo.getVendas());
        return dto;
    }

    public Veiculo converterParaEntidade(VeiculoDTO dto) {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(dto.getId());
        veiculo.setTipo(dto.getTipo());
        veiculo.setModelo(dto.getModelo());
        veiculo.setPlaca(dto.getPlaca());
        veiculo.setProprietario(dto.getProprietario());
        veiculo.setVendas(dto.getVendas());
        return veiculo;
    }

    public List<VeiculoDTO> converterParaListaDTO(List<Veiculo> veiculos) {
        return veiculos.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }
}
