package com.autobots.automanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entities.Credencial;

public interface CredencialRepository extends JpaRepository<Credencial, Long> {
    
}
