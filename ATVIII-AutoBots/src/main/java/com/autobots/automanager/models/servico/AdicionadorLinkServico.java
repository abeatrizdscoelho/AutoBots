package com.autobots.automanager.models.servico;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controllers.ServicoController;
import com.autobots.automanager.dtos.ServicoDTO;
import com.autobots.automanager.models.AdicionadorLink;

@Component
public class AdicionadorLinkServico implements AdicionadorLink<ServicoDTO> {
    @Override
    public void adicionarLink(List<ServicoDTO> lista) {
        for (ServicoDTO dto : lista) {
            long id = dto.getId();
            Link linkProprio = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(ServicoController.class)
                .obterServico(id))
                .withSelfRel();
            dto.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(ServicoDTO dto) {
        Link linkProprio = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(ServicoController.class)
            .obterServicos())
            .withRel("servicos");
        dto.add(linkProprio);
    }
}
