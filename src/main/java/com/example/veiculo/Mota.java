package com.example.veiculo;

import com.example.matricula.MatriculaTerrestre;

/**
 * Veículo do tipo Mota. Usa a estratégia {@link MatriculaTerrestre}
 * para gerar a sua matrícula (formato tipo {@code FS-13-HD}), tal como o {@link Carro}.
 */
public class Mota extends Veiculo {

    /**
     * Cria uma nova Mota com a marca e modelo indicados.
     * A matrícula é gerada automaticamente.
     *
     * @param marca  marca da mota (ex: "Yamaha")
     * @param modelo modelo da mota (ex: "MT-07")
     */
    public Mota(String marca, String modelo) {
        super(marca, modelo, new MatriculaTerrestre());
    }

    /**
     * {@inheritDoc}
     *
     * @return sempre {@link TipoVeiculo#MOTA}
     */
    @Override
    public TipoVeiculo getTipo() {
        return TipoVeiculo.MOTA;
    }
}
