package com.autobots.automanager.models.endereco;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controllers.EnderecoController;
import com.autobots.automanager.dtos.EnderecoDTO;
import com.autobots.automanager.models.AdicionadorLink;

@Component
public class AdicionadorLinkEndereco implements AdicionadorLink<EnderecoDTO> {
    @Override
    public void adicionarLink(List<EnderecoDTO> lista) {
        for (EnderecoDTO dto : lista) {
            long id = dto.getId();
            Link linkProprio = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(EnderecoController.class)
                .obterEndereco(id))
                .withSelfRel();
            dto.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(EnderecoDTO dto) {
        Link linkProprio = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(EnderecoController.class)
            .obterEnderecos())
            .withRel("enderecos");
        dto.add(linkProprio);
    }
}
