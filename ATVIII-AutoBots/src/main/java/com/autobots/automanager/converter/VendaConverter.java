package com.autobots.automanager.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.autobots.automanager.dtos.VendaDTO;
import com.autobots.automanager.entities.Venda;

@Component
public class VendaConverter {

    public VendaDTO converterParaDTO(Venda venda) {
        VendaDTO dto = new VendaDTO();
        dto.setId(venda.getId());
        dto.setCadastro(venda.getCadastro());
        dto.setIdentificacao(venda.getIdentificacao());
        dto.setCliente(venda.getCliente());
        dto.setFuncionario(venda.getFuncionario());
        dto.setVeiculo(venda.getVeiculo());
        dto.setMercadorias(venda.getMercadorias());
        dto.setServicos(venda.getServicos());
        return dto;
    }

    public Venda converterParaEntidade(VendaDTO dto) {
        Venda venda = new Venda();
        venda.setCadastro(dto.getCadastro());
        venda.setIdentificacao(dto.getIdentificacao());
        venda.setCliente(dto.getCliente());
        venda.setFuncionario(dto.getFuncionario());
        venda.setVeiculo(dto.getVeiculo());
        venda.setMercadorias(dto.getMercadorias());
        venda.setServicos(dto.getServicos());
        return venda;
    }

    public List<VendaDTO> converterParaListaDTO(List<Venda> vendas) {
        return vendas.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }
}
