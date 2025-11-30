package com.autobots.automanager.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.autobots.automanager.dtos.MercadoriaDTO;
import com.autobots.automanager.entities.Mercadoria;

@Component
public class MercadoriaConverter {

    public MercadoriaDTO converterParaDTO(Mercadoria mercadoria) {
        MercadoriaDTO dto = new MercadoriaDTO();
        dto.setId(mercadoria.getId());
        dto.setValidade(mercadoria.getValidade());
        dto.setFabricacao(mercadoria.getFabricacao());
        dto.setCadastro(mercadoria.getCadastro());
        dto.setNome(mercadoria.getNome());
        dto.setQuantidade(mercadoria.getQuantidade());
        dto.setValor(mercadoria.getValor());
        dto.setDescricao(mercadoria.getDescricao());
        return dto;
    }

    public Mercadoria converterParaEntidade(MercadoriaDTO dto) {
        Mercadoria mercadoria = new Mercadoria();
        mercadoria.setId(dto.getId());
        mercadoria.setValidade(dto.getValidade());
        mercadoria.setFabricacao(dto.getFabricacao());
        mercadoria.setCadastro(dto.getCadastro());
        mercadoria.setNome(dto.getNome());
        mercadoria.setQuantidade(dto.getQuantidade());
        mercadoria.setValor(dto.getValor());
        mercadoria.setDescricao(dto.getDescricao());
        return mercadoria;
    }

    public List<MercadoriaDTO> converterParaListaDTO(List<Mercadoria> lista) {
        return lista.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }
}
