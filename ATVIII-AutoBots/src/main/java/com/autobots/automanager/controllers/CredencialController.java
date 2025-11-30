package com.autobots.automanager.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.converter.CredencialConverter;
import com.autobots.automanager.dtos.CredencialCodigoBarraDTO;
import com.autobots.automanager.dtos.CredencialUsuarioSenhaDTO;
import com.autobots.automanager.entities.CredencialCodigoBarra;
import com.autobots.automanager.entities.CredencialUsuarioSenha;
import com.autobots.automanager.models.credencial.AdicionadorLinkCredencial;
import com.autobots.automanager.models.credencial.CredencialAtualizador;
import com.autobots.automanager.services.CredencialService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/credenciais")
public class CredencialController {

    @Autowired
    private CredencialService service;

    @Autowired
    private CredencialConverter converter;

    @Autowired
    private CredencialAtualizador atualizador;

    @Autowired
    private AdicionadorLinkCredencial adicionadorLink;

    @GetMapping("/usuario-senha")
    public ResponseEntity<List<CredencialUsuarioSenhaDTO>> listarUsuarioSenha() {
        List<CredencialUsuarioSenha> lista = service.listarUsuarioSenha();
        if (lista.isEmpty()) return ResponseEntity.notFound().build();
        List<CredencialUsuarioSenhaDTO> dtos = converter.converterParaListaUsuSenhaDTO(lista);
        adicionadorLink.adicionarLink(dtos);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/usuario-senha/{id}")
    public ResponseEntity<CredencialUsuarioSenhaDTO> buscarUsuarioSenhaPorId(@PathVariable Long id) {
        Optional<CredencialUsuarioSenha> opt = service.buscarUsuarioSenha(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        CredencialUsuarioSenhaDTO dto = converter.converterParaDTO(opt.get());
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/usuario-senha")
    public ResponseEntity<CredencialUsuarioSenhaDTO> criarUsuarioSenha(@Valid @RequestBody CredencialUsuarioSenhaDTO dto) {
        CredencialUsuarioSenha credencial = converter.converterParaEntidade(dto);
        CredencialUsuarioSenha salvo = service.salvarUsuarioSenha(credencial);
        CredencialUsuarioSenhaDTO salvoDto = converter.converterParaDTO(salvo);
        adicionadorLink.adicionarLink(salvoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvoDto);
    }

    @PutMapping("/usuario-senha/{id}")
    public ResponseEntity<CredencialUsuarioSenhaDTO> atualizarUsuarioSenha(@PathVariable Long id, @RequestBody CredencialUsuarioSenhaDTO dto) {
        Optional<CredencialUsuarioSenha> opt = service.buscarUsuarioSenha(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        CredencialUsuarioSenha existente = opt.get();
        CredencialUsuarioSenha atualizacao = converter.converterParaEntidade(dto);
        atualizador.atualizar(existente, atualizacao);
        CredencialUsuarioSenha salvo = service.salvarUsuarioSenha(existente);
        CredencialUsuarioSenhaDTO salvoDto = converter.converterParaDTO(salvo);
        adicionadorLink.adicionarLink(salvoDto);

        return ResponseEntity.ok(salvoDto);
    }

    @DeleteMapping("/usuario-senha/{id}")
    public ResponseEntity<Void> excluirUsuarioSenha(@PathVariable Long id) {
        Optional<CredencialUsuarioSenha> opt = service.buscarUsuarioSenha(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        service.deletarUsuarioSenha(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/codigo-barra")
    public ResponseEntity<List<CredencialCodigoBarraDTO>> listarCodigoBarra() {
        List<CredencialCodigoBarra> lista = service.listarCodigoBarra();
        if (lista.isEmpty()) return ResponseEntity.notFound().build();
        List<CredencialCodigoBarraDTO> dtos = converter.converterParaListaCodigoBarraDTO(lista);
        adicionadorLink.adicionarLinkCodigo(dtos);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/codigo-barra/{id}")
    public ResponseEntity<CredencialCodigoBarraDTO> buscarCodigoBarraPorId(@PathVariable Long id) {
        Optional<CredencialCodigoBarra> opt = service.buscarCodigoBarra(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        CredencialCodigoBarraDTO dto = converter.converterParaDTO(opt.get());
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/codigo-barra")
    public ResponseEntity<CredencialCodigoBarraDTO> criarCodigoBarra(@Validated @RequestBody CredencialCodigoBarraDTO dto) {
        CredencialCodigoBarra credencial = converter.converterParaEntidade(dto);
        CredencialCodigoBarra salvo = service.salvarCodigoBarra(credencial);
        CredencialCodigoBarraDTO salvoDto = converter.converterParaDTO(salvo);
        adicionadorLink.adicionarLink(salvoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvoDto);
    }

    @PutMapping("/codigo-barra/{id}")
    public ResponseEntity<CredencialCodigoBarraDTO> atualizarCodigoBarra(@PathVariable Long id, @RequestBody CredencialCodigoBarraDTO dto) {
        Optional<CredencialCodigoBarra> opt = service.buscarCodigoBarra(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        CredencialCodigoBarra existente = opt.get();
        CredencialCodigoBarra atualizacao = converter.converterParaEntidade(dto);
        atualizador.atualizar(existente, atualizacao);
        CredencialCodigoBarra salvo = service.salvarCodigoBarra(existente);
        CredencialCodigoBarraDTO salvoDto = converter.converterParaDTO(salvo);
        adicionadorLink.adicionarLink(salvoDto);

        return ResponseEntity.ok(salvoDto);
    }

    @DeleteMapping("/codigo-barra/{id}")
    public ResponseEntity<Void> excluirCodigoBarra(@PathVariable Long id) {
        Optional<CredencialCodigoBarra> opt = service.buscarCodigoBarra(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        service.deletarCodigoBarra(id);
        return ResponseEntity.noContent().build();
    }
}
