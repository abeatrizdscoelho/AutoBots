package com.autobots.automanager.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClienteDTO {
    private Long id;

    @NotBlank(message = "O nome é obrigatório!")
    private String nome;

    private String nomeSocial;

    @NotNull(message = "A data de nascimento é obrigatória!")
    private LocalDate dataNascimento;

    private LocalDate dataCadastro;
}
