package com.autobots.automanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entities.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    
}
