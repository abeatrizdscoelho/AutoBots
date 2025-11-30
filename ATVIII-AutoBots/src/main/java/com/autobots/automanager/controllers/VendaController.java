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

import com.autobots.automanager.converter.VendaConverter;
import com.autobots.automanager.dtos.VendaDTO;
import com.autobots.automanager.entities.Venda;
import com.autobots.automanager.models.venda.AdicionadorLinkVenda;
import com.autobots.automanager.models.venda.VendaAtualizador;
import com.autobots.automanager.services.VendaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    private VendaService service;

    @Autowired
    private VendaAtualizador atualizador;

    @Autowired
    private AdicionadorLinkVenda adicionadorLink;

    @Autowired
    private VendaConverter vendaConverter;

    @GetMapping("/listar")
    public ResponseEntity<List<VendaDTO>> obterVendas() {
        List<Venda> vendas = service.listar();
        if (vendas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<VendaDTO> dtos = vendaConverter.converterParaListaDTO(vendas);
        adicionadorLink.adicionarLink(dtos);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaDTO> obterVenda(@PathVariable long id) {
        Optional<Venda> vendaOptional = service.buscarPorId(id);
        if (vendaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Venda venda = vendaOptional.get();
        VendaDTO dto = vendaConverter.converterParaDTO(venda);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<VendaDTO> cadastrar(@Valid @RequestBody VendaDTO novaVenda) {
        Venda venda = vendaConverter.converterParaEntidade(novaVenda);
        Venda vendaSalva = service.salvar(venda);
        VendaDTO dto = vendaConverter.converterParaDTO(vendaSalva);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<VendaDTO> atualizar(@PathVariable long id, @RequestBody VendaDTO atualizacao) {
        Optional<Venda> vendaOptional = service.buscarPorId(id);
        if (vendaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Venda venda = vendaOptional.get();
        atualizador.atualizar(venda, atualizacao);
        Venda vendaAtualizada = service.salvar(venda);
        VendaDTO dto = vendaConverter.converterParaDTO(vendaAtualizada);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable long id) {
        Optional<Venda> vendaOptional = service.buscarPorId(id);
        if (vendaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
