package com.autobots.automanager.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entities.Email;
import com.autobots.automanager.repositories.EmailRepository;

@Service
public class EmailService {
    
    @Autowired
    private EmailRepository repository;

    public List<Email> listar() {
        return repository.findAll();
    }

    public Optional<Email> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Email salvar(Email email) {
        return repository.save(email);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
