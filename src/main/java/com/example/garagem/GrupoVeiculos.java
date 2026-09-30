package com.example.garagem;

import com.example.veiculo.ElementoGaragem;
import com.example.veiculo.Veiculo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Composto do padrão <b>Composite</b>: agrupa todos os veículos do mesmo
 * tipo (ex: todos os "Carros") dentro da {@link Garagem}.
 */
public class GrupoVeiculos implements ElementoGaragem, Iterable<Veiculo> {
    private final String nome;
    private final List<Veiculo> veiculos = new ArrayList<>();

    /**
     * Cria um novo grupo de veículos com o nome dado.
     *
     * @param nome nome do grupo, usado ao mostrar a garagem (ex: "Carros")
     */
    public GrupoVeiculos(String nome) {
        this.nome = nome;
    }

    /**
     * Adiciona um veículo a este grupo.
     *
     * @param veiculo veículo a adicionar
     */
    public void adicionar(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    /**
     * Remove um veículo deste grupo, caso exista.
     *
     * @param veiculo veículo a remover
     * @return {@code true} se o veículo foi removido, {@code false} se não estava no grupo
     */
    public boolean remover(Veiculo veiculo) {
        return veiculos.remove(veiculo);
    }

    /**
     * {@inheritDoc}
     * <p>Devolve o número de veículos presentes neste grupo.</p>
     */
    @Override
    public int contarVeiculos() {
        int total = 0;
        for (Veiculo v : veiculos) {
            total += v.contarVeiculos();
        }
        return total;
    }

    /**
     * {@inheritDoc}
     * <p>Mostra o nome do grupo seguido de cada veículo que contém.</p>
     */
    @Override
    public void mostrar(String indentacao) {
        System.out.println(indentacao + nome + " (" + contarVeiculos() + ")");
        for (Veiculo v : veiculos) {
            v.mostrar(indentacao + "  ");
        }
    }

    /**
     * Devolve um iterador só de leitura sobre os veículos deste grupo.
     *
     * @return iterador não modificável dos veículos do grupo
     */
    @Override
    public Iterator<Veiculo> iterator() {
        return Collections.unmodifiableList(veiculos).iterator();
    }
}
