package com.example;

public class MatriculaTerrestre extends Matricula {

    public static String gerar() {
        String parte1 = duasLetras();
        String parte2 = doisDigitos();
        String parte3 = duasLetras();
        return parte1 + "-" + parte2 + "-" + parte3;
    }
}