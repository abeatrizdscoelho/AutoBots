package com.autobots.automanager.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.autobots.automanager.entities.Endereco;
import com.autobots.automanager.entities.Mercadoria;
import com.autobots.automanager.entities.Servico;
import com.autobots.automanager.entities.Telefone;
import com.autobots.automanager.entities.Usuario;
import com.autobots.automanager.entities.Venda;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmpresaDTO extends RepresentationModel<EmpresaDTO>{
    
    private Long id;

    @NotBlank(message = "A razão social é obrigatória!")
    private String razaoSocial;

    private String nomeFantasia;

    @NotNull(message = "A data de cadastro é obrigatória!")
    private Date cadastro;

    @Valid
    private Set<Telefone> telefones = new HashSet<>();

    @Valid
    private Endereco endereco;

    private List<Usuario> usuarios = new ArrayList<>();

    private Set<Mercadoria> mercadorias = new HashSet<>();

    private Set<Servico> servicos = new HashSet<>();

    private Set<Venda> vendas = new HashSet<>();
}
