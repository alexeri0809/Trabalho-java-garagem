package com.example.facade;

import com.example.fabrica.BarcoFactory;
import com.example.fabrica.CarroFactory;
import com.example.fabrica.MotaFactory;
import com.example.fabrica.VeiculoFactory;
import com.example.garagem.Garagem;
import com.example.veiculo.TipoVeiculo;
import com.example.veiculo.Veiculo;

import java.util.EnumMap;
import java.util.Map;

/**
 * Padrão FACADE: interface simples para o Main. Esconde as fábricas,
 * a garagem, as matrículas e o iterador.
 */
public class GaragemFacade {
    private final Garagem garagem = new Garagem();
    private final Map<TipoVeiculo, VeiculoFactory> fabricas = new EnumMap<>(TipoVeiculo.class);

    public GaragemFacade() {
        fabricas.put(TipoVeiculo.CARRO, new CarroFactory());
        fabricas.put(TipoVeiculo.BARCO, new BarcoFactory());
        fabricas.put(TipoVeiculo.MOTA, new MotaFactory());
    }

    public int getLugaresLivres() {
        return garagem.getLugaresLivres();
    }

    public boolean adicionarVeiculo(TipoVeiculo tipo, String marca, String modelo) {
        if (garagem.getLugaresLivres() == 0) {
            System.out.println("A garagem está cheia! Não é possível adicionar mais veículos.");
            return false;
        }
        Veiculo veiculo = fabricas.get(tipo).criar(marca, modelo);
        garagem.adicionarVeiculo(veiculo);
        System.out.println("Estacionado: " + veiculo);
        return true;
    }

    public void mostrarGaragem() {
        garagem.mostrar("");
    }

    public void mostrarMatriculas() {
        if (garagem.getLugaresOcupados() == 0) {
            System.out.println("A garagem está vazia.");
            return;
        }
        System.out.println("--- Matrículas (ordem: Carro -> Barco -> Mota) ---");
        for (Veiculo veiculo : garagem) { // Iterator
            System.out.println(" - " + veiculo.getMatricula() + " (" + veiculo.getTipo().getNome() + ")");
        }
    }
}
