package com.dev.apresenta_ia.Exeption;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public record ErroResponse(
        LocalDateTime timestamp,
        Integer status,
        String erro,
        List<String> mensagens
) {
    // Construtor compacto — roda antes de atribuir os campos
    public ErroResponse {
        // - o método mensagens() devolve uma lista que não pode ser alterada
        mensagens = Collections.unmodifiableList(mensagens);
    }
}
