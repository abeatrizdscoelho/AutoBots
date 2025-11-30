package com.autobots.automanager.models.mercadoria;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controllers.MercadoriaController;
import com.autobots.automanager.dtos.MercadoriaDTO;
import com.autobots.automanager.models.AdicionadorLink;

@Component
public class AdicionadorLinkMercadoria implements AdicionadorLink<MercadoriaDTO> {
    @Override
    public void adicionarLink(List<MercadoriaDTO> lista) {
        for (MercadoriaDTO dto : lista) {
            long id = dto.getId();
            Link linkProprio = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(MercadoriaController.class)
                .obterMercadoria(id))
                .withSelfRel();
            dto.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(MercadoriaDTO dto) {
        Link linkProprio = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(MercadoriaController.class)
            .obterMercadorias())
            .withRel("mercadorias");
        dto.add(linkProprio);
    }
}
