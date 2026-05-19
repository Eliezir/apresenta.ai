package com.dev.apresenta_ia.Exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Captura todas as excessões de validação
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> tratarErroValidacao(final MethodArgumentNotValidException ex){
        final List<String> mensagens = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .toList();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErroResponse(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        "Erro de validação",
                        mensagens
        ));
    }

    // Trata o erro de Não Encontrado 404.
    @ExceptionHandler(ExcecaoNaoEncontrado.class)
    public ResponseEntity<ErroResponse> tratarErroNaoEncontrado(final ExcecaoNaoEncontrado ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErroResponse(
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND.value(),
                        "Recurso não encontrado",
                        List.of(ex.getMessage())
                ));
    }

    // Tratar validações de negócio lançadas manualmente nos Services
    @ExceptionHandler(ExcecaoValidacao.class)
    public ResponseEntity<ErroResponse> tratarExcecaoValidacao(final ExcecaoValidacao ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErroResponse(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        "Erro de validação",
                        List.of(ex.getMessage())));
    }

    // Captura qualquer ExcecaoApp que não foi tratada acima
    @ExceptionHandler(ExcecaoApp.class)
    public ResponseEntity<ErroResponse> tratarExcecaoApp(final ExcecaoApp ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ErroResponse(
                        LocalDateTime.now(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Erro interno da aplicação",
                        List.of(ex.getMessage())));
    }

}
