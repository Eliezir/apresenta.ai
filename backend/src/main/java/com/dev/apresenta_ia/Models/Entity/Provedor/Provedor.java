package com.dev.apresenta_ia.Models.Entity.Provedor;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "provedores")
@Entity
public class Provedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoProvedor tipo;

    @Column(nullable = false)
    private String apiKey;

    @Column(nullable = false)
    private Boolean ativo = true;

}
