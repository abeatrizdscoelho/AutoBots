package com.autobots.automanager.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.converter.DocumentoConverter;
import com.autobots.automanager.dtos.DocumentoDTO;
import com.autobots.automanager.entities.Documento;
import com.autobots.automanager.models.documento.AdicionadorLinkDocumento;
import com.autobots.automanager.models.documento.DocumentoAtualizador;
import com.autobots.automanager.services.DocumentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/documento")
public class DocumentoController {

    @Autowired
    private DocumentoService service;

    @Autowired
    private AdicionadorLinkDocumento adicionadorLink;

    @Autowired
    private DocumentoAtualizador atualizador;

    @Autowired
    private DocumentoConverter documentoConverter;

    @GetMapping("/listar")
    public ResponseEntity<List<DocumentoDTO>> obterDocumentos() {
        List<Documento> documentos = service.listar();
        if (documentos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<DocumentoDTO> dtos = documentoConverter.converterParaListaDTO(documentos);
        adicionadorLink.adicionarLink(dtos);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentoDTO> obterDocumento(@PathVariable long id) {
        Optional<Documento> documentoOptional = service.buscarPorId(id);
        if (documentoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Documento documento = documentoOptional.get();
        DocumentoDTO dto = documentoConverter.converterParaDTO(documento);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<DocumentoDTO> cadastrar(@Valid @RequestBody DocumentoDTO novoDocumento) {
        Documento documento = documentoConverter.converterParaEntidade(novoDocumento);
        Documento documentoSalvo = service.salvar(documento);
        DocumentoDTO dto = documentoConverter.converterParaDTO(documentoSalvo);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<DocumentoDTO> atualizar(@PathVariable long id, @RequestBody Documento atualizacao) {
        Optional<Documento> documentoOptional = service.buscarPorId(id);
        if (documentoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Documento documento = documentoOptional.get();
        atualizador.atualizar(documento, atualizacao);
        Documento documentoAtualizado = service.salvar(documento);
        DocumentoDTO dto = documentoConverter.converterParaDTO(documentoAtualizado);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable long id) {
        Optional<Documento> documentoOptional = service.buscarPorId(id);
        if (documentoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
