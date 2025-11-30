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

import com.autobots.automanager.converter.ServicoConverter;
import com.autobots.automanager.dtos.ServicoDTO;
import com.autobots.automanager.entities.Servico;
import com.autobots.automanager.models.servico.AdicionadorLinkServico;
import com.autobots.automanager.models.servico.ServicoAtualizador;
import com.autobots.automanager.services.ServicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/servico")
public class ServicoController {

    @Autowired
    private ServicoService service;

    @Autowired
    private AdicionadorLinkServico adicionadorLink;

    @Autowired
    private ServicoAtualizador atualizador;

    @Autowired
    private ServicoConverter servicoConverter;

    @GetMapping("/listar")
    public ResponseEntity<List<ServicoDTO>> obterServicos() {
        List<Servico> servicos = service.listar();
        if (servicos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<ServicoDTO> dtos = servicoConverter.converterParaListaDTO(servicos);
        adicionadorLink.adicionarLink(dtos);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoDTO> obterServico(@PathVariable long id) {
        Optional<Servico> servicoOptional = service.buscarPorId(id);
        if (servicoOptional.isEmpty()) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Servico servico = servicoOptional.get();
        ServicoDTO dto = servicoConverter.converterParaDTO(servico);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ServicoDTO> cadastrar(@Valid @RequestBody ServicoDTO novoServico) {
        Servico servico = servicoConverter.converterParaEntidade(novoServico);
        Servico servicoSalvo = service.salvar(servico);
        ServicoDTO dto = servicoConverter.converterParaDTO(servicoSalvo);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ServicoDTO> atualizar(@PathVariable long id, @RequestBody Servico atualizacao) {
        Optional<Servico> servicoOptional = service.buscarPorId(id);
        if (servicoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Servico servico = servicoOptional.get();
        atualizador.atualizar(servico, atualizacao);
        Servico servicoAtualizada = service.salvar(servico);
        ServicoDTO dto = servicoConverter.converterParaDTO(servicoAtualizada);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable long id) {
        Optional<Servico> servicoOptional = service.buscarPorId(id);
        if (servicoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
