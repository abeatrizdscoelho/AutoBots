package com.autobots.automanager.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(exclude = "empresa")
public class Mercadoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Date validade;
    @Column(nullable = false)
    private Date fabricacao;
    @Column(nullable = false)
    private Date cadastro;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private long quantidade;
    @Column(nullable = false)
    private double valor;
    @Column()
    private String descricao;
    @ManyToOne
	@JsonBackReference
	private Empresa empresa;
}
