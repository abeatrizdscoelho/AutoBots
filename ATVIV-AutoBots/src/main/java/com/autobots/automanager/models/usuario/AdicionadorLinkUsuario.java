package com.autobots.automanager.models.usuario;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controllers.UsuarioController;
import com.autobots.automanager.dtos.UsuarioDTO;
import com.autobots.automanager.models.AdicionadorLink;

@Component
public class AdicionadorLinkUsuario implements AdicionadorLink<UsuarioDTO> {
    @Override
    public void adicionarLink(List<UsuarioDTO> lista) {
        for (UsuarioDTO dto : lista) {
            long id = dto.getId();
            Link linkProprio = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
                .obterUsuario(id))
                .withSelfRel();
            dto.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(UsuarioDTO dto) {
        Link linkProprio = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class)
            .obterUsuarios())
            .withRel("Usuarios");
        dto.add(linkProprio);
    }
}
