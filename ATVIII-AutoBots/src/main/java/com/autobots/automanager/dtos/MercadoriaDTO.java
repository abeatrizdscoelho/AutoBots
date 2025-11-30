package com.autobots.automanager.dtos;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MercadoriaDTO extends RepresentationModel<MercadoriaDTO> {
    private Long id;

    @NotNull(message = "A data de validade é obrigatória!")
    private Date validade;

    @NotNull(message = "A data de fabricação é obrigatória!")
    private Date fabricacao;

    @NotNull(message = "A data de cadastro é obrigatória!")
    private Date cadastro;

    @NotBlank(message = "O nome da mercadoria é obrigatório!")
    private String nome;

    @NotNull(message = "A quantidade é obrigatória!")
    private Long quantidade;

    @NotNull(message = "O valor é obrigatório!")
    private Double valor;

    private String descricao;
}
