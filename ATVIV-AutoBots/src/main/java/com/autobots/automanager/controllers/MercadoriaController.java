package com.autobots.automanager.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.converter.MercadoriaConverter;
import com.autobots.automanager.dtos.MercadoriaDTO;
import com.autobots.automanager.entities.Mercadoria;
import com.autobots.automanager.models.mercadoria.AdicionadorLinkMercadoria;
import com.autobots.automanager.models.mercadoria.MercadoriaAtualizador;
import com.autobots.automanager.services.MercadoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/mercadoria")
public class MercadoriaController {

    @Autowired
    private MercadoriaService service;

    @Autowired
    private AdicionadorLinkMercadoria adicionadorLink;

    @Autowired
    private MercadoriaAtualizador atualizador;

    @Autowired
    private MercadoriaConverter mercadoriaConverter;

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @GetMapping("/listar")
    public ResponseEntity<List<MercadoriaDTO>> obterMercadorias() {
        List<Mercadoria> mercadorias = service.listar();
        if (mercadorias.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<MercadoriaDTO> dtos = mercadoriaConverter.converterParaListaDTO(mercadorias);
        adicionadorLink.adicionarLink(dtos);
        return ResponseEntity.ok(dtos);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE', 'VENDEDOR')")
    @GetMapping("/{id}")
    public ResponseEntity<MercadoriaDTO> obterMercadoria(@PathVariable long id) {
        Optional<Mercadoria> mercadoriaOptional = service.buscarPorId(id);
        if (mercadoriaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Mercadoria mercadoria = mercadoriaOptional.get();
        MercadoriaDTO dto = mercadoriaConverter.converterParaDTO(mercadoria);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @PostMapping("/cadastrar")
    public ResponseEntity<MercadoriaDTO> cadastrar(@Valid @RequestBody MercadoriaDTO novaMercadoria) {
        Mercadoria mercadoria = mercadoriaConverter.converterParaEntidade(novaMercadoria);
        Mercadoria mercadoriaSalva = service.salvar(mercadoria);
        MercadoriaDTO dto = mercadoriaConverter.converterParaDTO(mercadoriaSalva);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<MercadoriaDTO> atualizar(@PathVariable long id, @RequestBody Mercadoria atualizacao) {
        Optional<Mercadoria> mercadoriaOptional = service.buscarPorId(id);
        if (mercadoriaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Mercadoria mercadoria = mercadoriaOptional.get();
        atualizador.atualizar(mercadoria, atualizacao);
        Mercadoria mercadoriaAtualizada = service.salvar(mercadoria);
        MercadoriaDTO dto = mercadoriaConverter.converterParaDTO(mercadoriaAtualizada);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GERENTE')")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable long id) {
        Optional<Mercadoria> mercadoriaOptional = service.buscarPorId(id);
        if (mercadoriaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
