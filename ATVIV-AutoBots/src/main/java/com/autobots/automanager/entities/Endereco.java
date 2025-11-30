package com.autobots.automanager.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O estado é obrigatório!")
    @Column(nullable = false)
    private String estado;

    @NotBlank(message = "A cidade é obrigatória!")
    @Column(nullable = false)
    private String cidade;

    @NotBlank(message = "O bairro é obrigatório!")
    @Column(nullable = false)
    private String bairro;

    @NotBlank(message = "A rua é obrigatória!")
    @Column(nullable = false)
    private String rua;

    @NotBlank(message = "O número é obrigatório!")
    @Column(nullable = false)
    private String numero;

    @NotBlank(message = "O código postal é obrigatório!")
    @Column(nullable = false)
    private String codigoPostal;

    @Column()
    private String informacoesAdicionais;
}
