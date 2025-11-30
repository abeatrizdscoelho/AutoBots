package com.autobots.automanager.dtos;

import org.springframework.hateoas.RepresentationModel;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TelefoneDTO extends RepresentationModel<TelefoneDTO> {

    private Long id;

    @NotBlank(message = "O DDD é obrigatório!")
    private String ddd;

    @NotBlank(message = "O número é obrigatório!")
    private String numero;
}
