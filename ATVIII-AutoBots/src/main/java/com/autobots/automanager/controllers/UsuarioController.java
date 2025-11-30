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

import com.autobots.automanager.converter.CredencialConverter;
import com.autobots.automanager.converter.UsuarioConverter;
import com.autobots.automanager.dtos.CredencialUsuarioSenhaDTO;
import com.autobots.automanager.dtos.UsuarioDTO;
import com.autobots.automanager.entities.CredencialUsuarioSenha;
import com.autobots.automanager.entities.Usuario;
import com.autobots.automanager.models.usuario.AdicionadorLinkUsuario;
import com.autobots.automanager.models.usuario.UsuarioAtualizador;
import com.autobots.automanager.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private AdicionadorLinkUsuario adicionadorLink;

    @Autowired
    private UsuarioAtualizador atualizador;

    @Autowired
    private UsuarioConverter usuarioConverter;

    @Autowired 
    private CredencialConverter credencialConverter;

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioDTO>> obterUsuarios() {
        List<Usuario> usuarios = service.listar();
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        List<UsuarioDTO> dtos = usuarioConverter.converterParaListaDTO(usuarios);
        adicionadorLink.adicionarLink(dtos);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obterUsuario(@PathVariable long id) {
        Optional<Usuario> usuarioOptional = service.buscarPorId(id);
        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Usuario usuario = usuarioOptional.get();
        UsuarioDTO dto = usuarioConverter.converterParaDTO(usuario);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioDTO> cadastrar(@Valid @RequestBody UsuarioDTO novoUsuario) {
        Usuario usuario = usuarioConverter.converterParaEntidade(novoUsuario);
        Usuario usuarioSalvo = service.salvar(usuario);
        UsuarioDTO dto = usuarioConverter.converterParaDTO(usuarioSalvo);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    // Inclui credencial para acesso de um usuário ao sistema
    @PostMapping("/{id}/credencial/usuario-senha")
    public ResponseEntity<Void> adicionarCredencialUsuarioSenha(@PathVariable Long id, @Valid @RequestBody CredencialUsuarioSenhaDTO dto) {
        CredencialUsuarioSenha novaCredencial = credencialConverter.converterParaEntidade(dto);
        Optional<Usuario> usuarioOptional = service.buscarPorId(id);
        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Usuario usuario = usuarioOptional.get();
        if (novaCredencial.getCriacao() == null) {
            novaCredencial.setCriacao(new java.util.Date()); 
        }
        usuario.getCredenciais().add(novaCredencial);
        service.salvar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build(); 
    }

    // Associa usuário a uma determinada empresa
    @PostMapping("/registrar/{empresaId}")
    public ResponseEntity<UsuarioDTO> registrarUsuario(@PathVariable Long empresaId, @RequestBody UsuarioDTO novoDTO) {
        Usuario novo = usuarioConverter.converterParaEntidade(novoDTO);
        Usuario salvo = service.registrarUsuario(empresaId, novo);
        UsuarioDTO dto = usuarioConverter.converterParaDTO(salvo);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<UsuarioDTO> atualizar(@PathVariable long id, @RequestBody Usuario atualizacao) {
        Optional<Usuario> usuarioOptional = service.buscarPorId(id);
        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Usuario usuario = usuarioOptional.get();
        atualizador.atualizar(usuario, atualizacao);
        Usuario usuarioAtualizado = service.salvar(usuario);
        UsuarioDTO dto = usuarioConverter.converterParaDTO(usuarioAtualizado);
        adicionadorLink.adicionarLink(dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@PathVariable long id) {
        Optional<Usuario> usuarioOptional = service.buscarPorId(id);
        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
