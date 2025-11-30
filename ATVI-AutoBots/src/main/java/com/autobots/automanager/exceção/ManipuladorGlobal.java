package com.autobots.automanager.exceção;

import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.autobots.automanager.dtos.ErroRespostaDTO;

@ControllerAdvice
public class ManipuladorGlobal {

    // Validação de DTO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroRespostaDTO> manipularValidacaoCampos(MethodArgumentNotValidException ex) {
        String mensagens = ex.getBindingResult().getFieldErrors().stream().map(f -> f.getDefaultMessage()).collect(Collectors.joining("; "));
        ErroRespostaDTO erro = new ErroRespostaDTO("Erro de validação", mensagens);
        return ResponseEntity.badRequest().body(erro);
    }
}
