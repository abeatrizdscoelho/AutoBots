package com.autobots.automanager.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.autobots.automanager.dtos.TelefoneDTO;
import com.autobots.automanager.entities.Telefone;

@Component
public class TelefoneConverter {

    public TelefoneDTO converterParaDTO(Telefone telefone) {
        TelefoneDTO dto = new TelefoneDTO();
        dto.setId(telefone.getId());
        dto.setDdd(telefone.getDdd());
        dto.setNumero(telefone.getNumero());
        return dto;
    }

    public Telefone converterParaEntidade(TelefoneDTO dto) {
        Telefone telefone = new Telefone();
        telefone.setId(dto.getId());
        telefone.setDdd(dto.getDdd());
        telefone.setNumero(dto.getNumero());
        return telefone;
    }

    public List<TelefoneDTO> converterParaListaDTO(List<Telefone> lista) {
        return lista.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }
}
