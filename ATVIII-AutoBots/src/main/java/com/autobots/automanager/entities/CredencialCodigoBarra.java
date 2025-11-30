package com.autobots.automanager.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class CredencialCodigoBarra extends Credencial {
    @Column(nullable = false, unique = true)
    private long codigo;
}
