package com.autobots.automanager.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Telefone {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O DDD é obrigatório!")
    @Size(min = 2, max = 2, message = "O DDD deve conter exatamente 2 dígitos.")
    @Column(nullable = false)
    private String ddd;

    @NotBlank(message = "O número é obrigatório!")
    @Pattern(regexp = "\\d{8,9}", message = "O número deve conter 8 ou 9 dígitos, apenas números.")
    @Column(nullable = false, length = 9)
    private String numero;
}
