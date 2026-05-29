package com.dev.apresenta_ia.BO;


import com.dev.apresenta_ia.DAO.Provedor.ProvedorDAO;
import com.dev.apresenta_ia.Models.Entity.Provedor.TipoProvedor;
import com.dev.apresenta_ia.Models.VO.Provedor.CriaProvedorVO;
import com.dev.apresenta_ia.Models.VO.Provedor.ListaProvedorVO;
import com.dev.apresenta_ia.Services.BO.ProvedorBO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class ProvedorBOTest {

    @Autowired
    private ProvedorBO provedorBO;

    @Autowired
    private ProvedorDAO provedorDAO;

    void setUp() {
        provedorDAO.deleteAll();
    }

    @Test
    void deve_adicionar_provedor(){
        final CriaProvedorVO criaProvedorVO = new CriaProvedorVO(
                "Claude Pessoal",
                TipoProvedor.CLAUDE,
                "minha-chave"
        );

        final ListaProvedorVO criado = provedorBO.criar(criaProvedorVO);

        assertEquals("Claude Pessoal", criado.nome());
        assertEquals(TipoProvedor.CLAUDE, criado.tipo());
        assertEquals(true, criado.ativo());
    }

    @Test
    void deve_listar_provedores(){
        provedorBO.criar(new CriaProvedorVO("Mock local",
                TipoProvedor.MOCK, "chave-1"));
        provedorBO.criar(new CriaProvedorVO("Claude pessoal",
                TipoProvedor.CLAUDE, "chave-2"));

        final List<ListaProvedorVO> lista = provedorBO.listar();

        assertEquals(2, lista.size());

    }
}
