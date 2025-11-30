package com.autobots.automanager.dtos;

import org.springframework.hateoas.RepresentationModel;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmailDTO extends RepresentationModel<EmailDTO> {

    private Long id;

    @NotBlank(message = "O endereço de email é obrigatório!")
    @Email(message = "O endereço de email deve ser válido!")
    private String endereco;
}
