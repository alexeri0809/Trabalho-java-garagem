package com.example.veiculo;

/**
 * Enumera os tipos de veículo suportados pela garagem.
 *
 * <p>A ordem das constantes é significativa: define a ordem em que os
 * grupos de veículos são listados pela {@link com.example.garagem.Garagem}
 * e percorridos pelo {@link com.example.garagem.IteradorGaragem}
 * (padrão <b>Iterator</b>) — Carro, depois Barco, depois Mota.</p>
 */
public enum TipoVeiculo {
    /** Veículo terrestre de 4 rodas. */
    CARRO("Carro", "Carros"),
    /** Veículo aquático. */
    BARCO("Barco", "Barcos"),
    /** Veículo terrestre de 2 rodas. */
    MOTA("Mota", "Motas");

    private final String nome;
    private final String nomePlural;

    /**
     * Cria uma constante do enum com o seu nome singular e plural.
     *
     * @param nome       nome singular do tipo de veículo (ex: "Carro")
     * @param nomePlural nome plural do tipo de veículo (ex: "Carros")
     */
    TipoVeiculo(String nome, String nomePlural) {
        this.nome = nome;
        this.nomePlural = nomePlural;
    }

    /**
     * Devolve o nome singular do tipo de veículo.
     *
     * @return nome singular (ex: "Carro")
     */
    public String getNome() {
        return nome;
    }

    /**
     * Devolve o nome plural do tipo de veículo, usado ao agrupar veículos
     * na {@link com.example.garagem.GrupoVeiculos}.
     *
     * @return nome plural (ex: "Carros")
     */
    public String getNomePlural() {
        return nomePlural;
    }
}
