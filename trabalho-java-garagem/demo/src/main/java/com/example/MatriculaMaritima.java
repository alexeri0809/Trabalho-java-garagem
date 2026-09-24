package com.example;

public class MatriculaMaritima extends Matricula {

    public static String gerar() {
        int distrito = random.nextInt(9) + 1; // 1 a 9
        String capitania = duasLetras();
        int letraChamada = random.nextInt(9) + 1; // 1 a 9
        String parte1 = doisDigitos();
        String parte2 = doisDigitos();
        return distrito + "º-" + capitania + "-" + letraChamada + "-" + parte1 + "-" + parte2;
    }
}