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
 * Padrão <b>Facade</b>: ponto de entrada único e simplificado para o
 * {@link com.example.Main}. Esconde a complexidade interna do subsistema
 * (as {@link VeiculoFactory fábricas} de cada tipo de veículo, a
 * {@link Garagem} propriamente dita, a geração de matrículas e o iterador),
 * expondo apenas os métodos de que o menu precisa.
 */
public class GaragemFacade {
    private final Garagem garagem = new Garagem();
    private final Map<TipoVeiculo, VeiculoFactory> fabricas = new EnumMap<>(TipoVeiculo.class);

    /**
     * Cria a facade, inicializando a garagem vazia e registando a fábrica
     * (padrão Factory Method) correspondente a cada tipo de veículo.
     */
    public GaragemFacade() {
        fabricas.put(TipoVeiculo.CARRO, new CarroFactory());
        fabricas.put(TipoVeiculo.BARCO, new BarcoFactory());
        fabricas.put(TipoVeiculo.MOTA, new MotaFactory());
    }

    /**
     * Devolve o número de lugares ainda livres na garagem.
     *
     * @return número de lugares livres, entre 0 e 5
     */
    public int getLugaresLivres() {
        return garagem.getLugaresLivres();
    }

    /**
     * Cria e adiciona um novo veículo à garagem, usando a fábrica adequada
     * ao tipo indicado.
     *
     * @param tipo   tipo de veículo a criar (Carro, Barco ou Mota)
     * @param marca  marca do veículo
     * @param modelo modelo do veículo
     * @return {@code true} se o veículo foi adicionado com sucesso,
     *         {@code false} se a garagem já estava cheia
     */
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

    /**
     * Imprime no ecrã o estado atual da garagem, agrupado por tipo de veículo.
     */
    public void mostrarGaragem() {
        garagem.mostrar("");
    }

    /**
     * Imprime no ecrã só as matrículas de todos os veículos da garagem,
     * pela ordem Carro &rarr; Barco &rarr; Mota (usando o padrão Iterator
     * da {@link Garagem}).
     */
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
