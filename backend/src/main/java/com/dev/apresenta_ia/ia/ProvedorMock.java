package com.dev.apresenta_ia.ia;

import com.dev.apresenta_ia.Models.Entity.Provedor.TipoProvedor;

public class ProvedorMock extends ProvedorIA {

    private static final String HTML_FIXO = """
            <!DOCTYPE html>
            <html lang="pt-BR">
            <head>
                <meta charset="UTF-8">
                <title>Slides Mock</title>
            </head>
            <body>
                <section class="slide">
                    <h1>Apresenta AI</h1>
                    <p>Slide gerado pelo provedor mock.</p>
                </section>
            </body>
            </html>
            """;

    public ProvedorMock(String nome, String apiKey) {
        super(nome, apiKey, TipoProvedor.MOCK);
    }

    @Override
    public String gerarSlides() {
        return HTML_FIXO;
    }

    @Override
    public boolean testarConexao() {
        return true;
    }
}
