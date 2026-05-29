package com.dev.apresenta_ia.Models.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModeloEntity {

    private Integer id;
    private String nome;
    private String corPrimaria;
    private String fonte;
    private String templateHtml;

    public void validar() {
        if (this.nome == null || this.nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do modelo não pode estar vazio!");
        }
        if (this.templateHtml == null || this.templateHtml.trim().isEmpty()) {
            throw new IllegalArgumentException("O código HTML do template não pode estar vazio!");
        }
    }
}
