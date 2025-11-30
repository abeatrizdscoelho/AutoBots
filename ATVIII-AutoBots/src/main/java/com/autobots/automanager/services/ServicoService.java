package com.autobots.automanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entities.Servico;
import com.autobots.automanager.repositories.ServicoRepository;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository repository;

    public List<Servico> listar() {
        return repository.findAll();
    }

    public Optional<Servico> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Servico salvar(Servico servico) {
        return repository.save(servico);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
