package com.autobots.automanager.entities;

import java.util.Date;

import com.autobots.automanager.enumerations.TipoDocumento;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "O tipo de documento é obrigatório!")
    @Column(nullable = false)
    private TipoDocumento tipo;

    @NotNull(message = "A data de emissão é obrigatória!")
    @Column(nullable = false)
    private Date dataEmissao;

    @NotBlank(message = "O número do documento é obrigatório!")
    @Column(unique =  true, nullable = false)
    private String numero;
}
