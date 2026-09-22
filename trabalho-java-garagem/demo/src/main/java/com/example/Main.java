package com.example;

public class Main {
    public static void main(String[] args) {
        Garagem garagem = new Garagem();

        Carro carro = new Carro("Toyota", "Corolla");
        Barco barco = new Barco("Quicksilver", "activ 470");
        Mota mota = new Mota("Yamaha", "MT-07");

        // Ordem obrigatória: Carro -> Barco -> Mota
        garagem.adicionarVeiculo(carro);
        garagem.adicionarVeiculo(barco);
        garagem.adicionarVeiculo(mota);

        garagem.listarVeiculos();
    }
}