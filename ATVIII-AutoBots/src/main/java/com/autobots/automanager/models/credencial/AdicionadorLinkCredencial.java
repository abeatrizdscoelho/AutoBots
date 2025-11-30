package com.autobots.automanager.models.credencial;

import java.util.List;

import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controllers.CredencialController;
import com.autobots.automanager.dtos.CredencialCodigoBarraDTO;
import com.autobots.automanager.dtos.CredencialUsuarioSenhaDTO;

@Component
public class AdicionadorLinkCredencial {

    public void adicionarLink(CredencialUsuarioSenhaDTO dto) {
        Link selfLink = linkTo(methodOn(CredencialController.class)
            .listarUsuarioSenha())
            .withSelfRel();
        dto.add(selfLink);
    }

    public void adicionarLink(List<CredencialUsuarioSenhaDTO> dtos) {
        for (CredencialUsuarioSenhaDTO credencial : dtos) {
            adicionarLink(credencial);
        }
    }

    public void adicionarLink(CredencialCodigoBarraDTO dto) {
        Link selfLink = linkTo(methodOn(CredencialController.class)
            .listarCodigoBarra())
            .withSelfRel();
        dto.add(selfLink);
    }

    public void adicionarLinkCodigo(List<CredencialCodigoBarraDTO> dtos) {
        for (CredencialCodigoBarraDTO credencial : dtos) {
            adicionarLink(credencial);
        }
    }
}
