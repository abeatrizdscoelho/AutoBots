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

import com.autobots.automanager.converter.EmailConverter;
import com.autobots.automanager.dtos.EmailDTO;
import com.autobots.automanager.entities.Email;
import com.autobots.automanager.models.email.AdicionadorLinkEmail;
import com.autobots.automanager.models.email.EmailAtualizador;
import com.autobots.automanager.services.EmailService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService service;

    @Autowired
    private AdicionadorLinkEmail adicionadorLink;

    @Autowired
    private EmailAtualizador atualizador;

    @Autowired
    private EmailConverter emailConverter;

    @GetMapping("/listar")
    public ResponseEntity<List<EmailDTO>> obterEmails() {
        List<Email> emails = service.listar();
        if (emails.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<EmailDTO> dtos = emailConverter.converterParaListaDTO(emails);
        adicionadorLink.adicionarLink(dtos);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmailDTO> obterEmail(@PathVariable long id) {
        Optional<Email> emailOptional = service.buscarPorId(id);
        if (emailOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Email email = emailOptional.get();
        EmailDTO dto = emailConverter.converterParaDTO(email);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<EmailDTO> cadastrar(@Valid @RequestBody EmailDTO novoEmail) {
        Email email = emailConverter.converterParaEntidade(novoEmail);
        Email emailSalvo = service.salvar(email);
        EmailDTO dto = emailConverter.converterParaDTO(emailSalvo);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<EmailDTO> atualizar(@PathVariable long id, @RequestBody Email atualizacao) {
        Optional<Email> emailOptional = service.buscarPorId(id);
        if (emailOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Email email = emailOptional.get();
        atualizador.atualizar(email, atualizacao);
        Email emailAtualizado = service.salvar(email);
        EmailDTO dto = emailConverter.converterParaDTO(emailAtualizado);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable long id) {
        Optional<Email> emailOptional = service.buscarPorId(id);
        if (emailOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
