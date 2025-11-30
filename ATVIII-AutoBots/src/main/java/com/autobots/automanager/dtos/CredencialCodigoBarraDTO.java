package com.autobots.automanager.dtos;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CredencialCodigoBarraDTO extends RepresentationModel<CredencialCodigoBarraDTO>{
    private Long id;

    @NotNull(message = "O código de barras é obrigatório!")
    private Long codigo;

    @NotNull(message = "A data de criação é obrigatória!")
    private Date criacao;

    private Date ultimoAcesso;

    private boolean inativo;
}
