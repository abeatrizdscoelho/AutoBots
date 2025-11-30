package com.autobots.automanager.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DocumentoDTO {
    private Long id;

    @NotBlank(message = "O tipo é obrigatório!")
    private String tipo;

    @NotBlank(message = "O número é obrigatório!")
    private String numero;
}
