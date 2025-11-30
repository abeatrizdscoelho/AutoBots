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

import com.autobots.automanager.converter.EmpresaConverter;
import com.autobots.automanager.dtos.EmpresaDTO;
import com.autobots.automanager.entities.Empresa;
import com.autobots.automanager.models.empresa.AdicionadorLinkEmpresa;
import com.autobots.automanager.models.empresa.EmpresaAtualizador;
import com.autobots.automanager.services.EmpresaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService service;

    @Autowired
    private AdicionadorLinkEmpresa adicionadorLink;

    @Autowired
    private EmpresaAtualizador atualizador;

    @Autowired
    private EmpresaConverter empresaConverter;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/listar")
    public ResponseEntity<List<EmpresaDTO>> obterEmpresas() {
        List<Empresa> empresas = service.listar();
        if (empresas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<EmpresaDTO> dtos = empresaConverter.converterParaListaDTO(empresas);
        adicionadorLink.adicionarLink(dtos);
        return ResponseEntity.ok(dtos);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO> obterEmpresa(@PathVariable long id) {
        Optional<Empresa> empresaOptional = service.buscarPorId(id);
        if (empresaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Empresa empresa = empresaOptional.get();
        EmpresaDTO dto = empresaConverter.converterParaDTO(empresa);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<EmpresaDTO> cadastrar(@Valid @RequestBody EmpresaDTO novaEmpresa) {
        Empresa empresa = empresaConverter.converterParaEntidade(novaEmpresa);
        Empresa empresaSalva = service.salvar(empresa);
        EmpresaDTO dto = empresaConverter.converterParaDTO(empresaSalva);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<EmpresaDTO> atualizar(@PathVariable long id, @RequestBody Empresa atualizacao) {
        Optional<Empresa> empresaOptional = service.buscarPorId(id);
        if (empresaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Empresa empresa = empresaOptional.get();
        atualizador.atualizar(empresa, atualizacao);
        Empresa empresaAtualizada = service.salvar(empresa);
        EmpresaDTO dto = empresaConverter.converterParaDTO(empresaAtualizada);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable long id) {
        Optional<Empresa> empresaOptional = service.buscarPorId(id);
        if (empresaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
