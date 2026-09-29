package com.example;

import com.example.facade.GaragemFacade;
import com.example.veiculo.TipoVeiculo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GaragemFacade garagem = new GaragemFacade();
        Scanner scanner = new Scanner(System.in);

        boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- Lugares livres: " + garagem.getLugaresLivres() + " ---");
            System.out.println("1 - Adicionar Carro");
            System.out.println("2 - Adicionar Barco");
            System.out.println("3 - Adicionar Mota");
            System.out.println("4 - Ver garagem");
            System.out.println("5 - Ver matrículas");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> adicionarVeiculo(scanner, garagem, TipoVeiculo.CARRO);
                case "2" -> adicionarVeiculo(scanner, garagem, TipoVeiculo.BARCO);
                case "3" -> adicionarVeiculo(scanner, garagem, TipoVeiculo.MOTA);
                case "4" -> garagem.mostrarGaragem();
                case "5" -> garagem.mostrarMatriculas();
                case "0" -> continuar = false;
                default -> System.out.println("Opção inválida. Tenta novamente.");
            }
        }

        System.out.println("\nEstado final:");
        garagem.mostrarGaragem();
        scanner.close();
    }

    private static void adicionarVeiculo(Scanner scanner, GaragemFacade garagem, TipoVeiculo tipo) {
        if (garagem.getLugaresLivres() == 0) {
            System.out.println("A garagem está cheia! Não é possível adicionar mais veículos.");
            return;
        }

        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        garagem.adicionarVeiculo(tipo, marca, modelo);
    }
}
