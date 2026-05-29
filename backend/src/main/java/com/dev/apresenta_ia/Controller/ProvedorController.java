package com.dev.apresenta_ia.Controller;

import com.dev.apresenta_ia.Models.VO.Provedor.CriaProvedorVO;
import com.dev.apresenta_ia.Models.VO.Provedor.ListaProvedorVO;
import com.dev.apresenta_ia.Services.BO.ProvedorBO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provedores")
public class ProvedorController {
    private final ProvedorBO provedorBO;

    public ProvedorController(final ProvedorBO provedorBO) {
        this.provedorBO = provedorBO;
    }

    @PostMapping
    public ResponseEntity<ListaProvedorVO> criar(
            @Valid @RequestBody final CriaProvedorVO criaProvedorVO
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(provedorBO.criar(criaProvedorVO));
    }

    @GetMapping
    public ResponseEntity<List<ListaProvedorVO>> listar(){
        return ResponseEntity.ok(provedorBO.listar());
    }

}