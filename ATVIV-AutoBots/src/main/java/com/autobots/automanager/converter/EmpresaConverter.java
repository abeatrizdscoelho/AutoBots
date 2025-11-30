package com.autobots.automanager.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.autobots.automanager.dtos.EmpresaDTO;
import com.autobots.automanager.entities.Empresa;

@Component
public class EmpresaConverter {
    
    public EmpresaDTO converterParaDTO(Empresa empresa) {
        EmpresaDTO dto = new EmpresaDTO();
        dto.setId(empresa.getId());
        dto.setRazaoSocial(empresa.getRazaoSocial());
        dto.setNomeFantasia(empresa.getNomeFantasia());
        dto.setCadastro(empresa.getCadastro());
        dto.setEndereco(empresa.getEndereco());
        dto.setTelefones(empresa.getTelefones());
        dto.setUsuarios(empresa.getUsuarios());
        dto.setMercadorias(empresa.getMercadorias());
        dto.setServicos(empresa.getServicos());
        dto.setVendas(empresa.getVendas());
        return dto;
    }

    public Empresa converterParaEntidade(EmpresaDTO dto) {
        Empresa empresa = new Empresa();
        empresa.setId(dto.getId());
        empresa.setRazaoSocial(dto.getRazaoSocial());
        empresa.setNomeFantasia(dto.getNomeFantasia());
        empresa.setCadastro(dto.getCadastro());
        empresa.setEndereco(dto.getEndereco());
        empresa.setTelefones(dto.getTelefones());
        empresa.setUsuarios(dto.getUsuarios());
        empresa.setMercadorias(dto.getMercadorias());
        empresa.setServicos(dto.getServicos());
        empresa.setVendas(dto.getVendas());
        if (dto.getUsuarios() != null) {
            empresa.setUsuarios(new ArrayList<>(dto.getUsuarios()));
        }
        return empresa;
    }

    public List<EmpresaDTO> converterParaListaDTO(List<Empresa> empresas) {
        return empresas.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }
}
