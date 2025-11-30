package com.autobots.automanager.models.telefone;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controllers.TelefoneController;
import com.autobots.automanager.dtos.TelefoneDTO;
import com.autobots.automanager.models.AdicionadorLink;

@Component
public class AdicionadorLinkTelefone implements AdicionadorLink<TelefoneDTO> {
    @Override
    public void adicionarLink(List<TelefoneDTO> lista) {
        for (TelefoneDTO dto : lista) {
            long id = dto.getId();
            Link linkProprio = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(TelefoneController.class)
                .obterTelefone(id))
                .withSelfRel();
            dto.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(TelefoneDTO dto) {
        Link linkProprio = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(TelefoneController.class)
            .obterTelefones())
            .withRel("telefones");
        dto.add(linkProprio);
    }
}
