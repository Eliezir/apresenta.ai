package com.dev.apresenta_ia.Exeption;

import java.io.Serial;

public class ExcecaoApp extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public ExcecaoApp(final String mensagem) {
        super(mensagem);
    }
}
