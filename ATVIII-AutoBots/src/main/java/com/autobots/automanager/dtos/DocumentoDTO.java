package com.autobots.automanager.dtos;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.autobots.automanager.enumerations.TipoDocumento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DocumentoDTO extends RepresentationModel<DocumentoDTO> {

    private Long id;

    @NotNull(message = "O tipo de documento é obrigatório!")
    private TipoDocumento tipo;

    @NotNull(message = "A data de emissão é obrigatória!")
    private Date dataEmissao;

    @NotBlank(message = "O número do documento é obrigatório!")
    private String numero;
}
