package com.autobots.automanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entities.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Long>{
    
}
