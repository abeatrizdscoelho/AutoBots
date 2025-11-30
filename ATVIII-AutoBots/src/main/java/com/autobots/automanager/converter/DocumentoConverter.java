package com.autobots.automanager.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.autobots.automanager.dtos.DocumentoDTO;
import com.autobots.automanager.entities.Documento;

@Component
public class DocumentoConverter {

    public DocumentoDTO converterParaDTO(Documento documento) {
        DocumentoDTO dto = new DocumentoDTO();
        dto.setId(documento.getId());
        dto.setTipo(documento.getTipo());
        dto.setDataEmissao(documento.getDataEmissao());
        dto.setNumero(documento.getNumero());
        return dto;
    }

    public Documento converterParaEntidade(DocumentoDTO dto) {
        Documento documento = new Documento();
        documento.setId(dto.getId());
        documento.setTipo(dto.getTipo());
        documento.setDataEmissao(dto.getDataEmissao());
        documento.setNumero(dto.getNumero());
        return documento;
    }

    public List<DocumentoDTO> converterParaListaDTO(List<Documento> lista) {
        return lista.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }
}
