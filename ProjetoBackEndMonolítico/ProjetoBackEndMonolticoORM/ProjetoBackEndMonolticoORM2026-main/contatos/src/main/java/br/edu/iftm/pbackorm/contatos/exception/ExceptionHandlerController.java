package br.edu.iftm.pbackorm.contatos.exception;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.edu.iftm.pbackorm.contatos.dto.ErroDTO;
import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionHandlerController {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroDTO> tratarBadRequest(MethodArgumentNotValidException ex) {
        List<String> erros = ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(e -> e.getField() + ": " + e.getDefaultMessage())
                        .toList();

        ErroDTO erro = ErroDTO.builder()
                .dataHora(LocalDateTime.now())
                .erros(erros)
                .mensagem("Erro de Validação")
                .build();

        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErroDTO> tratarNaoEncontrado(EntityNotFoundException ex) {
        ErroDTO erro = ErroDTO.builder()
                .dataHora(LocalDateTime.now())
                .erros(Arrays.asList(ex.getMessage()))
                .mensagem("Não Encontrado")
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

}
