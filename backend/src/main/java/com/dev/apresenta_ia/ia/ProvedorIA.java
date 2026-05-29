package com.dev.apresenta_ia.ia;

import com.dev.apresenta_ia.Models.Entity.Provedor.TipoProvedor;

public abstract class ProvedorIA implements IGeradorSlides {

    private final String nome;
    private final String apiKey;
    private final TipoProvedor tipo;

    protected ProvedorIA(String nome, String apiKey, TipoProvedor tipo) {
        this.nome = nome;
        this.apiKey = apiKey;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getApiKey() {
        return apiKey;
    }

    public TipoProvedor getTipo() {
        return tipo;
    }
}
