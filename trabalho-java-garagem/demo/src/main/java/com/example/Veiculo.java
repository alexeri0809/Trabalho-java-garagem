package com.example;

public abstract class Veiculo {
    protected String matricula;
    protected String marca;
    protected String modelo;

    public Veiculo(String marca, String modelo) {
        this.matricula = GeradorMatricula.gerar();
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [" + matricula + " - " + marca + " " + modelo + "]";
    }
}