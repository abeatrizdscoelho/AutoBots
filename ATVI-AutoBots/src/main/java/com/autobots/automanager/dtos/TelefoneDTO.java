package com.autobots.automanager.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TelefoneDTO {
    private Long id;

    @NotBlank(message = "DDD é obrigatório!")
    private String ddd;

    @NotBlank(message = "Número é obrigatório!")
    private String numero;
}
