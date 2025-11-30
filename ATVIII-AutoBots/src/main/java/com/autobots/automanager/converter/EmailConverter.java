package com.autobots.automanager.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.autobots.automanager.dtos.EmailDTO;
import com.autobots.automanager.entities.Email;

@Component
public class EmailConverter {

    public EmailDTO converterParaDTO(Email email) {
        EmailDTO dto = new EmailDTO();
        dto.setId(email.getId());
        dto.setEndereco(email.getEndereco());
        return dto;
    }

    public Email converterParaEntidade(EmailDTO dto) {
        Email email = new Email();
        email.setId(dto.getId());
        email.setEndereco(dto.getEndereco());
        return email;
    }

    public List<EmailDTO> converterParaListaDTO(List<Email> lista) {
        return lista.stream().map(this::converterParaDTO).collect(Collectors.toList());
    }
}
