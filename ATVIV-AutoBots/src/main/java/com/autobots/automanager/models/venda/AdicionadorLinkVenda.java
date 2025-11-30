package com.autobots.automanager.models.venda;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controllers.VendaController;
import com.autobots.automanager.dtos.VendaDTO;
import com.autobots.automanager.models.AdicionadorLink;

@Component
public class AdicionadorLinkVenda implements AdicionadorLink<VendaDTO> {
    @Override
    public void adicionarLink(List<VendaDTO> lista) {
        for (VendaDTO dto : lista) {
            long id = dto.getId();
            Link linkProprio = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(VendaController.class)
                .obterVenda(id))
                .withSelfRel();
            dto.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(VendaDTO dto) {
        Link linkProprio = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(VendaController.class)
            .obterVendas())
            .withRel("vendas");
        dto.add(linkProprio);
    }
}
