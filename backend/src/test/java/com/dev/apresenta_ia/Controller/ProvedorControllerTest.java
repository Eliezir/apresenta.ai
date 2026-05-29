package com.dev.apresenta_ia.Controller;

import com.dev.apresenta_ia.DAO.Provedor.ProvedorDAO;
import com.dev.apresenta_ia.Models.Entity.Provedor.Provedor;
import com.dev.apresenta_ia.Models.Entity.Provedor.TipoProvedor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import
        org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static
        org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static
        org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
        ;
import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static
        org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
        ;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProvedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProvedorDAO provedorDAO;

    @BeforeEach
    void setUp() {
        provedorDAO.deleteAll();
    }

    @Test
    void deveCadastrarProvedor() throws Exception {
        final String body = """
                  {
                    "nome": "Claude pessoal",
                    "tipo": "CLAUDE",
                    "apiKey": "minha-chave"
                  }
                  """;

        mockMvc.perform(post("/provedores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Claude pessoal"))
                .andExpect(jsonPath("$.tipo").value("CLAUDE"))
                .andExpect(jsonPath("$.ativo").value(true));
    }

    @Test
    void deveListarProvedores() throws Exception {
        provedorDAO.save(Provedor.builder()
                .nome("Mock local")
                .tipo(TipoProvedor.MOCK)
                .apiKey("chave-mock")
                .ativo(true)
                .build());

        mockMvc.perform(get("/provedores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Mock local"))
                .andExpect(jsonPath("$[0].tipo").value("MOCK"));
    }
}
