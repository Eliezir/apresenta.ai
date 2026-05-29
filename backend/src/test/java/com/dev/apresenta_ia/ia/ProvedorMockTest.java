package com.dev.apresenta_ia.ia;

import com.dev.apresenta_ia.Models.Entity.Provedor.TipoProvedor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProvedorMockTest {

    @Test
    void deveRetornarHtmlFixoAoGerarSlides() {
        ProvedorMock provedorMock = new ProvedorMock("Mock local", "chave-teste");

        String html = provedorMock.gerarSlides();

        assertTrue(html.contains("<html"));
        assertTrue(html.contains("Slide gerado pelo provedor mock."));
    }

    @Test
    void deveRetornarConexaoValidaNoMock() {
        ProvedorMock provedorMock = new ProvedorMock("Mock local", "chave-teste");

        assertTrue(provedorMock.testarConexao());
    }

    @Test
    void deveHerdarOsDadosComunsDoProvedorIa() {
        ProvedorMock provedorMock = new ProvedorMock("Mock local", "chave-teste");

        assertEquals("Mock local", provedorMock.getNome());
        assertEquals("chave-teste", provedorMock.getApiKey());
        assertEquals(TipoProvedor.MOCK, provedorMock.getTipo());
    }
}
