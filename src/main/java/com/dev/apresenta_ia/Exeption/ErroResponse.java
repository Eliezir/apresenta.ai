package com.dev.apresenta_ia.Exeption;

import java.time.LocalDateTime;
import java.util.List;

public record ErroResponse(
        LocalDateTime timestamp,
        Integer status,
        String erro,
        List<String> mensagens
) {
}
