package com.autobots.automanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entities.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long>{
    
}
