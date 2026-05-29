package com.dev.apresenta_ia.Services.BO;

import com.dev.apresenta_ia.DAO.Provedor.ProvedorDAO;
import com.dev.apresenta_ia.Models.Entity.Provedor.Provedor;
import com.dev.apresenta_ia.Models.VO.Provedor.CriaProvedorVO;
import com.dev.apresenta_ia.Models.VO.Provedor.ListaProvedorVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvedorBO {
    private final ProvedorDAO provedorDAO;

    public ProvedorBO(ProvedorDAO provedorDAO) {
        this.provedorDAO = provedorDAO;
    }

    public ListaProvedorVO criar(final CriaProvedorVO criaProvedorVO) {
        final Provedor provedor = Provedor.builder()
                .nome(criaProvedorVO.nome())
                .tipo(criaProvedorVO.tipo())
                .apiKey(criaProvedorVO.apiKey())
                .ativo(true)
                .build();

        final Provedor salvo = provedorDAO.save(provedor);
        return paraVO(salvo);
    }

    public List<ListaProvedorVO> listar() {
        return provedorDAO.findAll()
                .stream()
                .map(this::paraVO)
                .toList();
    }

    public ListaProvedorVO paraVO(final Provedor provedor) {
        return new ListaProvedorVO(
                provedor.getId(),
                provedor.getNome(),
                provedor.getTipo(),
                provedor.getAtivo()
        );
    }
}
