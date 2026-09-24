package com.example;

import java.util.Random;

public abstract class Matricula {
    protected static final String LETRAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    protected static final Random random = new Random();

    protected static String duasLetras() {
        char c1 = LETRAS.charAt(random.nextInt(LETRAS.length()));
        char c2 = LETRAS.charAt(random.nextInt(LETRAS.length()));
        return "" + c1 + c2;
    }

    protected static String doisDigitos() {
        int numero = random.nextInt(100); // 0 a 99
        return String.format("%02d", numero);
    }
}