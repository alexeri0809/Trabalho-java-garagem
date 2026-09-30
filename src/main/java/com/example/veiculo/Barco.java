package com.example.veiculo;

import com.example.matricula.MatriculaMaritima;

/**
 * Veículo do tipo Barco. Usa a estratégia {@link MatriculaMaritima}
 * para gerar a sua matrícula (formato tipo {@code 6º-CO-7-15-21}),
 * diferente do formato usado por {@link Carro} e {@link Mota}.
 */
public class Barco extends Veiculo {

    /**
     * Cria um novo Barco com a marca e modelo indicados.
     * A matrícula é gerada automaticamente, no formato marítimo.
     *
     * @param marca  marca do barco (ex: "Quicksilver")
     * @param modelo modelo do barco (ex: "Activ 470")
     */
    public Barco(String marca, String modelo) {
        super(marca, modelo, new MatriculaMaritima());
    }

    /**
     * {@inheritDoc}
     *
     * @return sempre {@link TipoVeiculo#BARCO}
     */
    @Override
    public TipoVeiculo getTipo() {
        return TipoVeiculo.BARCO;
    }
}
