package com.autobots.automanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entities.Documento;
import com.autobots.automanager.repositories.DocumentoRepository;

@Service
public class DocumentoService {
    
    @Autowired
    private DocumentoRepository repository;

    public List<Documento> listar() {
        return repository.findAll();
    }

    public Optional<Documento> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Documento salvar(Documento documento) {
        return repository.save(documento);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
