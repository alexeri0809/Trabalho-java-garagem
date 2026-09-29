package com.example.veiculo;

import com.example.matricula.EstrategiaMatricula;

/** Folha do Composite: um veículo individual. */
public abstract class Veiculo implements ElementoGaragem {
    protected final String matricula;
    protected final String marca;
    protected final String modelo;

    public Veiculo(String marca, String modelo, EstrategiaMatricula estrategiaMatricula) {
        this.matricula = estrategiaMatricula.gerar(); // Strategy
        this.marca = marca;
        this.modelo = modelo;
    }

    public abstract TipoVeiculo getTipo();

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
    public int contarVeiculos() {
        return 1;
    }

    @Override
    public void mostrar(String indentacao) {
        System.out.println(indentacao + "- " + this);
    }

    @Override
    public String toString() {
        return getTipo().getNome() + " [" + matricula + " - " + marca + " " + modelo + "]";
    }
}
