package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Garagem garagem = new Garagem();
        Scanner scanner = new Scanner(System.in);

        boolean continuar = true;

        while (continuar) {
            System.out.println("\n--- Lugares livres: " + garagem.getLugaresLivres() + " ---");
            System.out.println("1 - Adicionar Carro");
            System.out.println("2 - Adicionar Barco");
            System.out.println("3 - Adicionar Mota");
            System.out.println("4 - Ver garagem");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1" -> adicionarVeiculo(scanner, garagem, "Carro");
                case "2" -> adicionarVeiculo(scanner, garagem, "Barco");
                case "3" -> adicionarVeiculo(scanner, garagem, "Mota");
                case "4" -> garagem.listarVeiculos();
                case "0" -> continuar = false;
                default -> System.out.println("Opção inválida. Tenta novamente.");
            }
        }

        System.out.println("\nEstado final:");
        garagem.listarVeiculos();
        scanner.close();
    }

    private static void adicionarVeiculo(Scanner scanner, Garagem garagem, String tipo) {
        if (garagem.getLugaresLivres() == 0) {
            System.out.println("A garagem está cheia! Não é possível adicionar mais veículos.");
            return;
        }

        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();

        Veiculo veiculo = switch (tipo) {
            case "Carro" -> new Carro(marca, modelo);
            case "Barco" -> new Barco(marca, modelo);
            case "Mota" -> new Mota(marca, modelo);
            default -> null;
        };

        if (veiculo != null) {
            garagem.adicionarVeiculo(veiculo);
        }
    }
}