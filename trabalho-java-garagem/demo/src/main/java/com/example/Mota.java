package com.example;

public class Mota extends Veiculo {
    public Mota(String marca, String modelo) {
        super(marca, modelo);
    }

    @Override
    protected String gerarMatricula() {
        return MatriculaTerrestre.gerar();
    }
}