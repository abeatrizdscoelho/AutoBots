package com.autobots.automanager.dtos;

import org.springframework.hateoas.RepresentationModel;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EnderecoDTO extends RepresentationModel<EnderecoDTO> {

    private Long id;

    @NotBlank(message = "O estado é obrigatório!")
    private String estado;

    @NotBlank(message = "A cidade é obrigatória!")
    private String cidade;

    @NotBlank(message = "O bairro é obrigatório!")
    private String bairro;

    @NotBlank(message = "A rua é obrigatória!")
    private String rua;

    @NotBlank(message = "O número é obrigatório!")
    private String numero;

    @NotBlank(message = "O código postal é obrigatório!")
    private String codigoPostal;

    private String informacoesAdicionais;
}
