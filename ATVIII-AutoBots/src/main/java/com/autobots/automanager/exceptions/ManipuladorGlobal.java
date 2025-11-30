package com.autobots.automanager.exceptions;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.autobots.automanager.dtos.ErroRespostaDTO;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class ManipuladorGlobal {

    // Validação de DTO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroRespostaDTO> manipularValidacaoCampos(MethodArgumentNotValidException ex) {
        String mensagens = ex.getBindingResult().getFieldErrors().stream().map(f -> f.getDefaultMessage()).collect(Collectors.joining("; "));
        ErroRespostaDTO erro = new ErroRespostaDTO("Erro de validação", mensagens);
        return ResponseEntity.badRequest().body(erro);
    }

    // Exception Entidade Não Encontrada
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErroRespostaDTO> manipularEntityNotFound(EntityNotFoundException ex) {
        ErroRespostaDTO erro = new ErroRespostaDTO("Recurso não encontrado", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
}
