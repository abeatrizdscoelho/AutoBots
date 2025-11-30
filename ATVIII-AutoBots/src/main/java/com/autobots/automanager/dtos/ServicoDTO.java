package com.autobots.automanager.dtos;

import org.springframework.hateoas.RepresentationModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ServicoDTO extends RepresentationModel<ServicoDTO> {

    private Long id;

    @NotBlank(message = "O nome do serviço é obrigatório!")
    private String nome;

    @NotNull(message = "O valor do serviço é obrigatório!")
    private Double valor;

    private String descricao;
}
