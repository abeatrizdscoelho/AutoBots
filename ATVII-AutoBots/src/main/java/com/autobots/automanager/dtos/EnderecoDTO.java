package com.autobots.automanager.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EnderecoDTO {
    private Long id;

    private String estado;

    @NotBlank(message = "Cidade é obrigatória!")
    private String cidade;

    private String bairro;

    @NotBlank(message = "Rua é obrigatória!")
    private String rua;

    @NotBlank(message = "Número é obrigatório!")
    private String numero;

    private String codigoPostal;
    
    private String informacoesAdicionais;
}
