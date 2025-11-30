package com.autobots.automanager.models.empresa;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controllers.EmpresaController;
import com.autobots.automanager.dtos.EmpresaDTO;
import com.autobots.automanager.models.AdicionadorLink;

@Component
public class AdicionadorLinkEmpresa implements AdicionadorLink<EmpresaDTO> {
    @Override
    public void adicionarLink(List<EmpresaDTO> lista) {
        for (EmpresaDTO dto : lista) {
            long id = dto.getId();
            Link linkProprio = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(EmpresaController.class)
                .obterEmpresa(id))
                .withSelfRel();
            dto.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(EmpresaDTO dto) {
        Link linkProprio = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(EmpresaController.class)
            .obterEmpresas())
            .withRel("empresas");
        dto.add(linkProprio);
    }
}
