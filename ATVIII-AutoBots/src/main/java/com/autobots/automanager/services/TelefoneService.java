package com.autobots.automanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entities.Telefone;
import com.autobots.automanager.repositories.TelefoneRepository;

@Service
public class TelefoneService {
    
    @Autowired
    private TelefoneRepository repository;

    public List<Telefone> listar() {
        return repository.findAll();
    }

    public Optional<Telefone> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Telefone salvar(Telefone telefone) {
        return repository.save(telefone);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
