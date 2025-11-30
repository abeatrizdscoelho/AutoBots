package com.autobots.automanager.dtos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.autobots.automanager.entities.Mercadoria;
import com.autobots.automanager.entities.Servico;
import com.autobots.automanager.entities.Usuario;
import com.autobots.automanager.entities.Veiculo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VendaDTO extends RepresentationModel<VendaDTO> {
    
    private Long id;

    @NotNull(message = "A data de cadastro é obrigatória!")
    private Date cadastro;

    @NotBlank(message = "A identificação da venda é obrigatória!")
    private String identificacao;

    @NotNull(message = "O cliente é obrigatório!")
    private Usuario cliente;

    private Usuario vendedor;

    @NotNull(message = "O veículo é obrigatório!")
    private Veiculo veiculo;

    private Set<Mercadoria> mercadorias = new HashSet<>();

    private Set<Servico> servicos = new HashSet<>();
}
