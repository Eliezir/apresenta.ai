package com.dev.apresenta_ia.Processamento;

import com.dev.apresenta_ia.Models.Entity.Provedor.TipoProvedor;
import com.dev.apresenta_ia.Models.VO.Provedor.CriaProvedorVO;
import lombok.Getter;

public abstract class ProvedorIA implements IGeradorSlide {
    @Getter
    private final String nome;
    @Getter
    private final TipoProvedor tipo;
    @Getter
    private final String apiKey;

    protected ProvedorIA(final CriaProvedorVO criaProvedorVO) {
        this.nome = criaProvedorVO.nome();
        this.tipo = criaProvedorVO.tipo();
        this.apiKey = criaProvedorVO.apiKey();
    }

    @Override
    public abstract boolean testarConexao();

    @Override
    public abstract String gerarSlide();
}
