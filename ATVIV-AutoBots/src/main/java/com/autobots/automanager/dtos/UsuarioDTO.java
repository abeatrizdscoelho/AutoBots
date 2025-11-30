package com.autobots.automanager.dtos;

import java.util.HashSet;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.autobots.automanager.entities.Credencial;
import com.autobots.automanager.entities.Documento;
import com.autobots.automanager.entities.Email;
import com.autobots.automanager.entities.Empresa;
import com.autobots.automanager.entities.Endereco;
import com.autobots.automanager.entities.Mercadoria;
import com.autobots.automanager.entities.Telefone;
import com.autobots.automanager.entities.Veiculo;
import com.autobots.automanager.entities.Venda;
import com.autobots.automanager.enumerations.PerfilUsuario;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsuarioDTO extends RepresentationModel<UsuarioDTO>{

    private Long id;

    @NotBlank(message = "O nome é obrigatório!")
    private String nome;

    private String nomeSocial;

    @NotEmpty(message = "Pelo menos um Perfil de Usuário é obrigatório!")
    private Set<PerfilUsuario> perfis = new HashSet<>();

    @Valid
    @NotNull(message = "A credencial é obrigatória!")
    private Credencial credencial;

    @Valid
    @NotEmpty(message = "Pelo menos um Email é obrigatório!")
    private Set<Email> emails = new HashSet<>();

    @Valid
    private Set<Telefone> telefones = new HashSet<>();

    @Valid
    private Endereco endereco;

    @Valid
    private Set<Documento> documentos = new HashSet<>();

    private Set<Mercadoria> mercadorias = new HashSet<>();

    private Set<Venda> vendas = new HashSet<>();

    private Set<Veiculo> veiculos = new HashSet<>();

    private Long empresaId;
}
