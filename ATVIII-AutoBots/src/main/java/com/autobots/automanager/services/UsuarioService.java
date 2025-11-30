package com.autobots.automanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entities.Empresa;
import com.autobots.automanager.entities.Usuario;
import com.autobots.automanager.repositories.EmpresaRepository;
import com.autobots.automanager.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Usuario registrarUsuario(Long empresaId, Usuario novo) {
        Empresa empresa = empresaRepository.findById(empresaId).orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada"));
        novo.setEmpresa(empresa);      
        return repository.save(novo); 
    }
}
