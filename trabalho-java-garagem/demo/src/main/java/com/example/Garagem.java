package com.example;

import java.util.ArrayList;
import java.util.List;

public class Garagem {
    private static final int CAPACIDADE_MAXIMA = 5;
    private final List<Veiculo> lugares = new ArrayList<>();

    public boolean adicionarVeiculo(Veiculo veiculo) {
        if (lugares.size() >= CAPACIDADE_MAXIMA) {
            System.out.println("Garagem cheia! Não é possível estacionar: " + veiculo);
            return false;
        }
        lugares.add(veiculo);
        System.out.println("Estacionado: " + veiculo);
        return true;
    }

    public boolean removerVeiculo(Veiculo veiculo) {
        boolean removido = lugares.remove(veiculo);
        if (removido) {
            System.out.println("Removido: " + veiculo);
        }
        return removido;
    }

    public int getLugaresOcupados() {
        return lugares.size();
    }

    public int getLugaresLivres() {
        return CAPACIDADE_MAXIMA - lugares.size();
    }

    public void listarVeiculos() {
        System.out.println("--- Veículos na garagem (" + lugares.size() + "/" + CAPACIDADE_MAXIMA + ") ---");
        for (Veiculo v : lugares) {
            System.out.println(" - " + v);
        }
    }
}