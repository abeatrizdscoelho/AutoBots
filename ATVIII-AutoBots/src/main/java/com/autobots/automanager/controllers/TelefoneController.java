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

import com.autobots.automanager.converter.TelefoneConverter;
import com.autobots.automanager.dtos.TelefoneDTO;
import com.autobots.automanager.entities.Telefone;
import com.autobots.automanager.models.telefone.AdicionadorLinkTelefone;
import com.autobots.automanager.models.telefone.TelefoneAtualizador;
import com.autobots.automanager.services.TelefoneService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/telefone")
public class TelefoneController {

    @Autowired
    private TelefoneService service;

    @Autowired
    private AdicionadorLinkTelefone adicionadorLink;

    @Autowired
    private TelefoneAtualizador atualizador;

    @Autowired
    private TelefoneConverter telefoneConverter;

    @GetMapping("/listar")
    public ResponseEntity<List<TelefoneDTO>> obterTelefones() {
        List<Telefone> telefones = service.listar();
        if (telefones.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<TelefoneDTO> dtos = telefoneConverter.converterParaListaDTO(telefones);
        adicionadorLink.adicionarLink(dtos);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TelefoneDTO> obterTelefone(@PathVariable long id) {
        Optional<Telefone> telefoneOptional = service.buscarPorId(id);
        if (telefoneOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Telefone telefone = telefoneOptional.get();
        TelefoneDTO dto = telefoneConverter.converterParaDTO(telefone);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<TelefoneDTO> cadastrar(@Valid @RequestBody TelefoneDTO novoTelefone) {
        Telefone telefone = telefoneConverter.converterParaEntidade(novoTelefone);
        Telefone telefoneSalvo = service.salvar(telefone);
        TelefoneDTO dto = telefoneConverter.converterParaDTO(telefoneSalvo);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<TelefoneDTO> atualizar(@PathVariable long id, @RequestBody Telefone atualizacao) {
        Optional<Telefone> telefoneOptional = service.buscarPorId(id);
        if (telefoneOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Telefone telefone = telefoneOptional.get();
        atualizador.atualizar(telefone, atualizacao);
        Telefone telefoneAtualizado = service.salvar(telefone);
        TelefoneDTO dto = telefoneConverter.converterParaDTO(telefoneAtualizado);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable long id) {
        Optional<Telefone> telefoneOptional = service.buscarPorId(id);
        if (telefoneOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
