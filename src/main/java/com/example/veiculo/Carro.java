package com.example.veiculo;

import com.example.matricula.MatriculaTerrestre;

/**
 * Veículo do tipo Carro. Usa a estratégia {@link MatriculaTerrestre}
 * para gerar a sua matrícula (formato tipo {@code FS-13-HD}).
 */
public class Carro extends Veiculo {

    /**
     * Cria um novo Carro com a marca e modelo indicados.
     * A matrícula é gerada automaticamente.
     *
     * @param marca  marca do carro (ex: "Toyota")
     * @param modelo modelo do carro (ex: "Corolla")
     */
    public Carro(String marca, String modelo) {
        super(marca, modelo, new MatriculaTerrestre());
    }

    /**
     * {@inheritDoc}
     *
     * @return sempre {@link TipoVeiculo#CARRO}
     */
    @Override
    public TipoVeiculo getTipo() {
        return TipoVeiculo.CARRO;
    }
}
