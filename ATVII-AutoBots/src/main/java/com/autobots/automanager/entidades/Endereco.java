package com.autobots.automanager.entidades;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Endereco extends RepresentationModel<Endereco>{
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    private String estado;
    @Column(nullable = false)
    private String cidade;
    @Column(nullable = true)
    private String bairro;
    @Column(nullable = false)
    private String rua;
    @Column(nullable = false)
    private String numero;
    @Column(nullable = true)
    private String codigoPostal;
    @Column(unique = false, nullable = true)
    private String informacoesAdicionais;

    @OneToOne
    @JoinColumn(name = "cliente_id") 
    @JsonIgnore 
    private Cliente cliente;
}
