package com.example.matricula;

/**
 * Estratégia de matrícula para {@link com.example.veiculo.Barco}.
 * Formato: {@code 6º-CO-7-15-21} (distrito - capitania - dígito - 2 dígitos - 2 dígitos).
 */
public class MatriculaMaritima extends Matricula {

    /**
     * Gera uma matrícula no formato marítimo.
     *
     * @return matrícula no formato {@code Nº-AA-N-00-00}
     */
    @Override
    public String gerar() {
        int distrito = random.nextInt(9) + 1;      // 1 a 9
        int letraChamada = random.nextInt(9) + 1;  // 1 a 9
        return distrito + "º-" + duasLetras() + "-" + letraChamada
                + "-" + doisDigitos() + "-" + doisDigitos();
    }
}
