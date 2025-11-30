package com.autobots.automanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entities.CredencialCodigoBarra;
import com.autobots.automanager.entities.CredencialUsuarioSenha;
import com.autobots.automanager.repositories.CredencialCodigoBarraRepository;
import com.autobots.automanager.repositories.CredencialUsuarioSenhaRepository;

@Service
public class CredencialService {

    @Autowired
    private CredencialUsuarioSenhaRepository usuarioSenhaRepository;

    @Autowired
    private CredencialCodigoBarraRepository codigoBarraRepository;

    public List<CredencialUsuarioSenha> listarUsuarioSenha() {
        return usuarioSenhaRepository.findAll();
    }

    public CredencialUsuarioSenha salvarUsuarioSenha(CredencialUsuarioSenha credencial) {
        return usuarioSenhaRepository.save(credencial);
    }

    public Optional<CredencialUsuarioSenha> buscarUsuarioSenha(Long id) {
        return usuarioSenhaRepository.findById(id);
    }

    public void deletarUsuarioSenha(Long id) {
        usuarioSenhaRepository.deleteById(id);
    }

    public List<CredencialCodigoBarra> listarCodigoBarra() {
        return codigoBarraRepository.findAll();
    }

    public CredencialCodigoBarra salvarCodigoBarra(CredencialCodigoBarra credencial) {
        return codigoBarraRepository.save(credencial);
    }

    public Optional<CredencialCodigoBarra> buscarCodigoBarra(Long id) {
        return codigoBarraRepository.findById(id);
    }

    public void deletarCodigoBarra(Long id) {
        codigoBarraRepository.deleteById(id);
    }
}
