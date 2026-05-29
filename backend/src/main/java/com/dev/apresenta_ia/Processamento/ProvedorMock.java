package com.dev.apresenta_ia.Processamento;

import com.dev.apresenta_ia.Models.VO.Provedor.CriaProvedorVO;

public class ProvedorMock extends ProvedorIA {
    public ProvedorMock(final CriaProvedorVO criaProvedorVO) {
        super(criaProvedorVO);
    }

    @Override
    public boolean testarConexao() {
        return true;
    }

    @Override
    public String gerarSlide() {
        return """
                <!DOCTYPE html>
                <html lang="pt-BR">
                <head>
                    <meta charset="UTF-8">
                    <title>Slide Mock</title>
                </head>
                <body>
                    <section class="slide">
                        <h1>Apresenta AI</h1>
                        <p>Slide gerado pelo provedor mock.</p>
                    </section>
                </body>
                </html>
                """;
    }
}
