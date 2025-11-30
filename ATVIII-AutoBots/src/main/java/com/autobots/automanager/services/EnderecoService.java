package com.autobots.automanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entities.Endereco;
import com.autobots.automanager.repositories.EnderecoRepository;

@Service
public class EnderecoService {
    
    @Autowired
    private EnderecoRepository repository;

    public List<Endereco> listar() {
        return repository.findAll();
    }

    public Optional<Endereco> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Endereco salvar(Endereco endereco) {
        return repository.save(endereco);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
