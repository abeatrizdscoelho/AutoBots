package com.autobots.automanager.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.autobots.automanager.dtos.ServicoDTO;
import com.autobots.automanager.entities.Servico;

@Component
public class ServicoConverter {

    public ServicoDTO converterParaDTO(Servico servico) {
        ServicoDTO dto = new ServicoDTO();
        dto.setId(servico.getId());
        dto.setNome(servico.getNome());
        dto.setValor(servico.getValor());
        dto.setDescricao(servico.getDescricao());
        return dto;
    }

    public Servico converterParaEntidade(ServicoDTO dto) {
        Servico servico = new Servico();
        servico.setId(dto.getId());
        servico.setNome(dto.getNome());
        servico.setValor(dto.getValor());
        servico.setDescricao(dto.getDescricao());
        return servico;
    }

    public List<ServicoDTO> converterParaListaDTO(List<Servico> lista) {
        return lista.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }
}
