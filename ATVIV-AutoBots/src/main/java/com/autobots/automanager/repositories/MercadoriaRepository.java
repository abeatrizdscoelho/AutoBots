package com.autobots.automanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entities.Mercadoria;

public interface MercadoriaRepository extends JpaRepository<Mercadoria, Long>{
    
}
