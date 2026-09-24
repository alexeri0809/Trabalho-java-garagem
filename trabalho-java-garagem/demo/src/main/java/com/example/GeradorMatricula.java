package com.example;

import java.util.Random;

public class GeradorMatricula {
    private static final String LETRAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random random = new Random();

    public static String gerar() {
        String parte1 = duasLetras();
        String parte2 = doisDigitos();
        String parte3 = duasLetras();
        return parte1 + "-" + parte2 + "-" + parte3;
    }

    private static String duasLetras() {
        char c1 = LETRAS.charAt(random.nextInt(LETRAS.length()));
        char c2 = LETRAS.charAt(random.nextInt(LETRAS.length()));
        return "" + c1 + c2;
    }

    private static String doisDigitos() {
        int numero = random.nextInt(100); // 0 a 99
        return String.format("%02d", numero);
    }
}