package com.autobots.automanager.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErroRespostaDTO {
    private String titulo;
    private String mensagem;
}