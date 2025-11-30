package com.autobots.automanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entities.Email;

public interface EmailRepository extends JpaRepository<Email, Long>{
    
}
