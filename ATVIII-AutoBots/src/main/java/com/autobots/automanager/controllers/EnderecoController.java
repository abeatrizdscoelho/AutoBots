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

import com.autobots.automanager.converter.EnderecoConverter;
import com.autobots.automanager.dtos.EnderecoDTO;
import com.autobots.automanager.entities.Endereco;
import com.autobots.automanager.models.endereco.AdicionadorLinkEndereco;
import com.autobots.automanager.models.endereco.EnderecoAtualizador;
import com.autobots.automanager.services.EnderecoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @Autowired
    private AdicionadorLinkEndereco adicionadorLink;

    @Autowired
    private EnderecoAtualizador atualizador;

    @Autowired
    private EnderecoConverter enderecoConverter;

    @GetMapping("/listar")
    public ResponseEntity<List<EnderecoDTO>> obterEnderecos() {
        List<Endereco> enderecos = service.listar();
        if (enderecos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<EnderecoDTO> dtos = enderecoConverter.converterParaListaDTO(enderecos);
        adicionadorLink.adicionarLink(dtos);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDTO> obterEndereco(@PathVariable long id) {
        Optional<Endereco> enderecoOptional = service.buscarPorId(id);
        if (enderecoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Endereco endereco = enderecoOptional.get();
        EnderecoDTO dto = enderecoConverter.converterParaDTO(endereco);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<EnderecoDTO> cadastrar(@Valid @RequestBody EnderecoDTO novoEndereco) {
        Endereco endereco = enderecoConverter.converterParaEntidade(novoEndereco);
        Endereco enderecoSalvo = service.salvar(endereco);
        EnderecoDTO dto = enderecoConverter.converterParaDTO(enderecoSalvo);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<EnderecoDTO> atualizar(@PathVariable long id, @RequestBody Endereco atualizacao) {
        Optional<Endereco> enderecoOptional = service.buscarPorId(id);
        if (enderecoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Endereco endereco = enderecoOptional.get();
        atualizador.atualizar(endereco, atualizacao);
        Endereco enderecoAtualizado = service.salvar(endereco);
        EnderecoDTO dto = enderecoConverter.converterParaDTO(enderecoAtualizado);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable long id) {
        Optional<Endereco> enderecoOptional = service.buscarPorId(id);
        if (enderecoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
