package com.example.fabrica;

import com.example.veiculo.Carro;
import com.example.veiculo.Veiculo;

/**
 * Fábrica concreta (padrão Factory Method) que cria instâncias de {@link Carro}.
 */
public class CarroFactory extends VeiculoFactory {

    /**
     * {@inheritDoc}
     *
     * @return um novo {@link Carro}
     */
    @Override
    protected Veiculo criarVeiculo(String marca, String modelo) {
        return new Carro(marca, modelo);
    }
}
