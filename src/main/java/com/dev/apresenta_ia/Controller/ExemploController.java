package com.dev.apresenta_ia.Controller;

import com.dev.apresenta_ia.BO.ExemploBO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exemplo")
public class ExemploController {

    // Injeção de dependencia da camada de BO
    private ExemploBO exemploBO;

    public ExemploBO getExemploBO(ExemploBO exemploBO) {
        return exemploBO;
    }

    @GetMapping
    public String olaMundo (){
        return "Olá mundo";
    }
}
