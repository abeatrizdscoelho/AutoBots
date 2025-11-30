package com.autobots.automanager.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.autobots.automanager.dtos.EnderecoDTO;
import com.autobots.automanager.entities.Endereco;

@Component
public class EnderecoConverter {

    public EnderecoDTO converterParaDTO(Endereco endereco) {
        EnderecoDTO dto = new EnderecoDTO();
        dto.setId(endereco.getId());
        dto.setEstado(endereco.getEstado());
        dto.setCidade(endereco.getCidade());
        dto.setBairro(endereco.getBairro());
        dto.setRua(endereco.getRua());
        dto.setNumero(endereco.getNumero());
        dto.setCodigoPostal(endereco.getCodigoPostal());
        dto.setInformacoesAdicionais(endereco.getInformacoesAdicionais());
        return dto;
    }

    public Endereco converterParaEntidade(EnderecoDTO dto) {
        Endereco endereco = new Endereco();
        endereco.setId(dto.getId());
        endereco.setEstado(dto.getEstado());
        endereco.setCidade(dto.getCidade());
        endereco.setBairro(dto.getBairro());
        endereco.setRua(dto.getRua());
        endereco.setNumero(dto.getNumero());
        endereco.setCodigoPostal(dto.getCodigoPostal());
        endereco.setInformacoesAdicionais(dto.getInformacoesAdicionais());
        return endereco;
    }

    public List<EnderecoDTO> converterParaListaDTO(List<Endereco> lista) {
        return lista.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }
}
