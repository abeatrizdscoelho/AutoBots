package com.autobots.automanager.dtos;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CredencialUsuarioSenhaDTO extends RepresentationModel<CredencialUsuarioSenhaDTO>{
    private Long id;

    @NotBlank(message = "O nome do usuário é obrigatório!")
    private String nomeUsuario;

    @NotBlank(message = "A senha é obrigatória!")
    private String senha;

    @NotNull(message = "A data de criação é obrigatória!")
    private Date criacao;

    private Date ultimoAcesso;

    private boolean inativo;
}
