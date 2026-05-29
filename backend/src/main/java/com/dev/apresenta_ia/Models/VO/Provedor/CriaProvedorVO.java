package com.dev.apresenta_ia.Models.VO.Provedor;

import com.dev.apresenta_ia.Models.Entity.Provedor.TipoProvedor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriaProvedorVO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotNull(message = "O tipo é obrigatório")
        TipoProvedor tipo,

        @NotBlank(message = "A API_KEY é obrigatório")
        String apiKey
) {
}
