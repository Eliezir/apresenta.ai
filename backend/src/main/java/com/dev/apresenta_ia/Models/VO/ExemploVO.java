package com.dev.apresenta_ia.Models.VO;

// Record são classes imutáveis (readonly)
// aqui devem vir construtores e validações com Spring Validator
public record ExemploVO(
        Long id,
        String exemplo
) {
}
