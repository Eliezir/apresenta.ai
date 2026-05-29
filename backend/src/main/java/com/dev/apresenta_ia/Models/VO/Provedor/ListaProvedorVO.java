package com.dev.apresenta_ia.Models.VO.Provedor;


import com.dev.apresenta_ia.Models.Entity.Provedor.TipoProvedor;

public record ListaProvedorVO(
        Long id,
        String nome,
        TipoProvedor tipo,
        Boolean ativo
){
}
