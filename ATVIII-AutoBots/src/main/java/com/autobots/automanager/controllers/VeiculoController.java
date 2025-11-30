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

import com.autobots.automanager.converter.VeiculoConverter;
import com.autobots.automanager.dtos.VeiculoDTO;
import com.autobots.automanager.entities.Veiculo;
import com.autobots.automanager.models.veiculo.AdicionadorLinkVeiculo;
import com.autobots.automanager.models.veiculo.VeiculoAtualizador;
import com.autobots.automanager.services.VeiculoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService service;

    @Autowired
    private AdicionadorLinkVeiculo adicionadorLink;

    @Autowired
    private VeiculoAtualizador atualizador;

    @Autowired
    private VeiculoConverter veiculoConverter;

    @GetMapping("/listar")
    public ResponseEntity<List<VeiculoDTO>> obterVeiculos() {
        List<Veiculo> veiculos = service.listar();
        if (veiculos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<VeiculoDTO> dtos = veiculoConverter.converterParaListaDTO(veiculos);
        adicionadorLink.adicionarLink(dtos);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> obterVeiculo(@PathVariable long id) {
        Optional<Veiculo> veiculoOptional = service.buscarPorId(id);
        if (veiculoOptional.isEmpty()) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Veiculo veiculo = veiculoOptional.get();
        VeiculoDTO dto = veiculoConverter.converterParaDTO(veiculo);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<VeiculoDTO> cadastrar(@Valid @RequestBody VeiculoDTO novoVeiculo) {
        Veiculo veiculo = veiculoConverter.converterParaEntidade(novoVeiculo);
        Veiculo veiculoSalvo = service.salvar(veiculo);
        VeiculoDTO dto = veiculoConverter.converterParaDTO(veiculoSalvo);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<VeiculoDTO> atualizar(@PathVariable long id, @RequestBody Veiculo atualizacao) {
        Optional<Veiculo> veiculoOptional = service.buscarPorId(id);
        if (veiculoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Veiculo veiculo = veiculoOptional.get();
        atualizador.atualizar(veiculo, atualizacao);
        Veiculo veiculoAtualizada = service.salvar(veiculo);
        VeiculoDTO dto = veiculoConverter.converterParaDTO(veiculoAtualizada);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable long id) {
        Optional<Veiculo> veiculoOptional = service.buscarPorId(id);
        if (veiculoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
