package com.autobots.automanager.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.autobots.automanager.dtos.CredencialCodigoBarraDTO;
import com.autobots.automanager.dtos.CredencialUsuarioSenhaDTO;
import com.autobots.automanager.entities.CredencialCodigoBarra;
import com.autobots.automanager.entities.CredencialUsuarioSenha;

@Component
public class CredencialConverter {

    public CredencialUsuarioSenhaDTO converterParaDTO(CredencialUsuarioSenha entidade) {
        CredencialUsuarioSenhaDTO dto = new CredencialUsuarioSenhaDTO();
        dto.setId(entidade.getId());
        dto.setNomeUsuario(entidade.getNomeUsuario());
        dto.setSenha(entidade.getSenha());
        dto.setCriacao(entidade.getCriacao());
        dto.setUltimoAcesso(entidade.getUltimoAcesso());
        dto.setInativo(entidade.isInativo());
        return dto;
    }

    public CredencialUsuarioSenha converterParaEntidade(CredencialUsuarioSenhaDTO dto) {
        CredencialUsuarioSenha entidade = new CredencialUsuarioSenha();
        entidade.setId(dto.getId());
        entidade.setNomeUsuario(dto.getNomeUsuario());
        entidade.setSenha(dto.getSenha());
        entidade.setCriacao(dto.getCriacao());
        entidade.setUltimoAcesso(dto.getUltimoAcesso());
        entidade.setInativo(dto.isInativo());
        return entidade;
    }

    public List<CredencialUsuarioSenhaDTO> converterParaListaUsuSenhaDTO(List<CredencialUsuarioSenha> entidades) {
        return entidades.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }

    public CredencialCodigoBarraDTO converterParaDTO(CredencialCodigoBarra entidade) {
        CredencialCodigoBarraDTO dto = new CredencialCodigoBarraDTO();
        dto.setId(entidade.getId());
        dto.setCodigo(entidade.getCodigo());
        dto.setCriacao(entidade.getCriacao());
        dto.setUltimoAcesso(entidade.getUltimoAcesso());
        dto.setInativo(entidade.isInativo());
        return dto;
    }

    public CredencialCodigoBarra converterParaEntidade(CredencialCodigoBarraDTO dto) {
        CredencialCodigoBarra entidade = new CredencialCodigoBarra();
        entidade.setId(dto.getId());
        entidade.setCodigo(dto.getCodigo());
        entidade.setCriacao(dto.getCriacao());
        entidade.setUltimoAcesso(dto.getUltimoAcesso());
        entidade.setInativo(dto.isInativo());
        return entidade;
    }

    public List<CredencialCodigoBarraDTO> converterParaListaCodigoBarraDTO(List<CredencialCodigoBarra> entidades) {
        return entidades.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }
}
