package com.dev.apresenta_ia.Models.Entity;

import jakarta.persistence.*;
import lombok.Data;

// Entidade mapeada para o banco de dados
//@Entity
@Data
//@Table(name = "TAB_exemplo")
public class ExemploEntity {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String exemplo;

}
