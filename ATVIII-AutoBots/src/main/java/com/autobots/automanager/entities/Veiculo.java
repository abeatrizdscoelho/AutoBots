package com.autobots.automanager.entities;

import java.util.HashSet;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.autobots.automanager.enumerations.TipoVeiculo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@EqualsAndHashCode(exclude = {"proprietario", "vendas" })
public class Veiculo extends RepresentationModel<Veiculo> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private TipoVeiculo tipo;
    @Column(nullable = false)
    private String modelo;
    @Column(nullable = false)
    private String placa;
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JsonIgnore
    @ToString.Exclude
    private Usuario proprietario;
    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JsonIgnore
    @ToString.Exclude
    private Set<Venda> vendas = new HashSet<>();
}
