package com.example.fabrica;

import com.example.veiculo.Mota;
import com.example.veiculo.Veiculo;

/**
 * Fábrica concreta (padrão Factory Method) que cria instâncias de {@link Mota}.
 */
public class MotaFactory extends VeiculoFactory {

    /**
     * {@inheritDoc}
     *
     * @return uma nova {@link Mota}
     */
    @Override
    protected Veiculo criarVeiculo(String marca, String modelo) {
        return new Mota(marca, modelo);
    }
}
