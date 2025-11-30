package com.autobots.automanager.models.email;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controllers.EmailController;
import com.autobots.automanager.dtos.EmailDTO;
import com.autobots.automanager.models.AdicionadorLink;

@Component
public class AdicionadorLinkEmail implements AdicionadorLink<EmailDTO> {
    @Override
    public void adicionarLink(List<EmailDTO> lista) {
        for (EmailDTO dto : lista) {
            long id = dto.getId();
            Link linkProprio = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(EmailController.class)
                .obterEmail(id))
                .withSelfRel();
            dto.add(linkProprio);
        }
    }

    @Override
    public void adicionarLink(EmailDTO dto) {
        Link linkProprio = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(EmailController.class)
            .obterEmails())
            .withRel("emails");
        dto.add(linkProprio);
    }
}
