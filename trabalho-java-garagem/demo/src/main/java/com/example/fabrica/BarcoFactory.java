package com.example.fabrica;

import com.example.veiculo.Barco;
import com.example.veiculo.Veiculo;

public class BarcoFactory extends VeiculoFactory {
    @Override
    protected Veiculo criarVeiculo(String marca, String modelo) {
        return new Barco(marca, modelo);
    }
}
