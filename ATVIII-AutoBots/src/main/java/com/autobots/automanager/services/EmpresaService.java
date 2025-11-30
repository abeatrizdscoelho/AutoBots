package com.autobots.automanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entities.Empresa;
import com.autobots.automanager.repositories.EmpresaRepository;

@Service
public class EmpresaService {
    
    @Autowired
    private EmpresaRepository repository;

    public List<Empresa> listar() {
        return repository.findAll();
    }

    public Optional<Empresa> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Empresa salvar(Empresa empresa) {
        return repository.save(empresa);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
