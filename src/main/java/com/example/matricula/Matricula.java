package com.example.matricula;

import java.util.Random;

/**
 * Classe base abstrata para as estratégias concretas de geração de matrícula
 * ({@link MatriculaTerrestre} e {@link MatriculaMaritima}), fornecendo os
 * utilitários comuns de geração aleatória de letras e dígitos.
 */
public abstract class Matricula implements EstrategiaMatricula {
    /** Letras do alfabeto usadas na geração aleatória. */
    protected static final String LETRAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /** Gerador de números aleatórios partilhado por todas as estratégias. */
    protected static final Random random = new Random();

    /**
     * Gera duas letras maiúsculas aleatórias.
     *
     * @return uma string com exatamente 2 letras
     */
    protected String duasLetras() {
        char c1 = LETRAS.charAt(random.nextInt(LETRAS.length()));
        char c2 = LETRAS.charAt(random.nextInt(LETRAS.length()));
        return "" + c1 + c2;
    }

    /**
     * Gera dois dígitos aleatórios, sempre com zero à esquerda quando necessário.
     *
     * @return uma string com exatamente 2 dígitos (de "00" a "99")
     */
    protected String doisDigitos() {
        return String.format("%02d", random.nextInt(100)); // 00 a 99
    }
}
