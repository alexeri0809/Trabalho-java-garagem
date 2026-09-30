package com.example.veiculo;

import com.example.matricula.EstrategiaMatricula;

/**
 * Classe base abstrata de todos os veículos (folha do padrão <b>Composite</b>).
 *
 * <p>A matrícula é gerada automaticamente no construtor, através de uma
 * {@link EstrategiaMatricula} recebida pela subclasse concreta ({@link Carro},
 * {@link Barco} ou {@link Mota}), seguindo o padrão <b>Strategy</b>: cada tipo
 * de veículo usa um algoritmo diferente de geração de matrícula, sem que esta
 * classe precise de conhecer os detalhes desse algoritmo.</p>
 */
public abstract class Veiculo implements ElementoGaragem {
    /** Matrícula gerada automaticamente para este veículo. */
    protected final String matricula;
    /** Marca do veículo (ex: "Toyota"). */
    protected final String marca;
    /** Modelo do veículo (ex: "Corolla"). */
    protected final String modelo;

    /**
     * Cria um novo veículo, gerando a sua matrícula através da estratégia dada.
     *
     * @param marca               marca do veículo
     * @param modelo              modelo do veículo
     * @param estrategiaMatricula estratégia (padrão Strategy) usada para gerar a matrícula
     */
    public Veiculo(String marca, String modelo, EstrategiaMatricula estrategiaMatricula) {
        this.matricula = estrategiaMatricula.gerar(); // Strategy
        this.marca = marca;
        this.modelo = modelo;
    }

    /**
     * Devolve o {@link TipoVeiculo} concreto deste veículo.
     *
     * @return o tipo do veículo (Carro, Barco ou Mota)
     */
    public abstract TipoVeiculo getTipo();

    /**
     * Devolve a matrícula gerada automaticamente para este veículo.
     *
     * @return a matrícula do veículo
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * Devolve a marca do veículo.
     *
     * @return a marca do veículo
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Devolve o modelo do veículo.
     *
     * @return o modelo do veículo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * {@inheritDoc}
     * <p>Um veículo individual conta sempre como 1.</p>
     */
    @Override
    public int contarVeiculos() {
        return 1;
    }

    /**
     * {@inheritDoc}
     * <p>Imprime a representação textual deste veículo numa linha.</p>
     */
    @Override
    public void mostrar(String indentacao) {
        System.out.println(indentacao + "- " + this);
    }

    /**
     * Devolve a representação textual do veículo, no formato
     * {@code Tipo [matrícula - marca modelo]}.
     *
     * @return representação textual do veículo
     */
    @Override
    public String toString() {
        return getTipo().getNome() + " [" + matricula + " - " + marca + " " + modelo + "]";
    }
}
