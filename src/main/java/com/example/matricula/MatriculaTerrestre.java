package com.example.matricula;

/**
 * Estratégia de matrícula para veículos terrestres ({@link com.example.veiculo.Carro}
 * e {@link com.example.veiculo.Mota}). Formato: {@code FS-13-HD}
 * (2 letras - 2 dígitos - 2 letras).
 */
public class MatriculaTerrestre extends Matricula {

    /**
     * Gera uma matrícula no formato terrestre português.
     *
     * @return matrícula no formato {@code AA-00-AA}
     */
    @Override
    public String gerar() {
        return duasLetras() + "-" + doisDigitos() + "-" + duasLetras();
    }
}
