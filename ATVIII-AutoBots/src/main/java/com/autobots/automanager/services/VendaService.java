package com.autobots.automanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entities.Venda;
import com.autobots.automanager.repositories.VendaRepository;

@Service
public class VendaService {
    
    @Autowired
    private VendaRepository repository;

    public List<Venda> listar() {
        return repository.findAll();
    }

    public Optional<Venda> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Venda salvar(Venda venda) {
        return repository.save(venda);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
