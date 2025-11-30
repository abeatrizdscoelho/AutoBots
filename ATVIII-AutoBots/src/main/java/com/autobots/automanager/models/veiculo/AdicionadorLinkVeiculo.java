package com.autobots.automanager.models.veiculo;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controllers.VeiculoController;
import com.autobots.automanager.dtos.VeiculoDTO;
import com.autobots.automanager.models.AdicionadorLink;

@Component
public class AdicionadorLinkVeiculo implements AdicionadorLink<VeiculoDTO> {
    @Override
    public void adicionarLink(List<VeiculoDTO> lista) {
        for (VeiculoDTO dto : lista) {
            long id = dto.getId();
            Link linkProprio = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(VeiculoController.class)
                .obterVeiculo(id))
                .withSelfRel();
            dto.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(VeiculoDTO dto) {
        Link linkProprio = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(VeiculoController.class)
            .obterVeiculos())
            .withRel("veiculos");
        dto.add(linkProprio);
    }
}
