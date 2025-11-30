package com.autobots.automanager.models.documento;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controllers.DocumentoController;
import com.autobots.automanager.dtos.DocumentoDTO;
import com.autobots.automanager.models.AdicionadorLink;

@Component
public class AdicionadorLinkDocumento implements AdicionadorLink<DocumentoDTO> {
    @Override
    public void adicionarLink(List<DocumentoDTO> lista) {
        for (DocumentoDTO dto : lista) {
            long id = dto.getId();
            Link linkProprio = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(DocumentoController.class)
                .obterDocumento(id))
                .withSelfRel();
            dto.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(DocumentoDTO dto) {
        Link linkProprio = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(DocumentoController.class)
            .obterDocumentos())
            .withRel("documentos");
        dto.add(linkProprio);
    }
}
