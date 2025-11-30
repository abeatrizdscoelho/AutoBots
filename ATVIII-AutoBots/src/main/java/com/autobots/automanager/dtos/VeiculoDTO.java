package com.autobots.automanager.dtos;

import java.util.HashSet;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.autobots.automanager.entities.Usuario;
import com.autobots.automanager.entities.Venda;
import com.autobots.automanager.enumerations.TipoVeiculo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VeiculoDTO extends RepresentationModel<VeiculoDTO>{
    
    private Long id;

    @NotNull(message = "O tipo do veículo é obrigatório!")
    private TipoVeiculo tipo;

    @NotBlank(message = "O modelo do veículo é obrigatório!")
    private String modelo;

    @NotBlank(message = "A placa do veículo é obrigatória!")
    private String placa;

    private Usuario proprietario;

    private Set<Venda> vendas = new HashSet<>();
}
