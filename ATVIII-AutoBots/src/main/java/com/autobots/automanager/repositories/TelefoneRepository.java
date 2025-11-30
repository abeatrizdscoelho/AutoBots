package com.autobots.automanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entities.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long>{
    
}
