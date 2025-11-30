package com.autobots.automanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entities.Veiculo;
import com.autobots.automanager.repositories.VeiculoRepository;

@Service
public class VeiculoService {
    
    @Autowired
    private VeiculoRepository repository;

    public List<Veiculo> listar() {
        return repository.findAll();
    }

    public Optional<Veiculo> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Veiculo salvar(Veiculo veiculo) {
        return repository.save(veiculo);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
