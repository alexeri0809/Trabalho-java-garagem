package com.example.garagem;

import com.example.veiculo.Veiculo;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Padrão <b>Iterator</b>: percorre todos os veículos da {@link Garagem},
 * grupo a grupo, pela ordem definida em
 * {@link com.example.veiculo.TipoVeiculo} (Carro &rarr; Barco &rarr; Mota),
 * sem expor ao utilizador a estrutura interna da garagem
 * ({@code Map<TipoVeiculo, GrupoVeiculos>}).
 */
public class IteradorGaragem implements Iterator<Veiculo> {
    private final Iterator<GrupoVeiculos> grupos;
    private Iterator<Veiculo> atual;

    /**
     * Cria um iterador sobre a coleção de grupos de veículos dada.
     *
     * @param grupos coleção de grupos de veículos a percorrer, pela ordem
     *               em que forem devolvidos pelo seu próprio iterador
     */
    public IteradorGaragem(Collection<GrupoVeiculos> grupos) {
        this.grupos = grupos.iterator();
    }

    /**
     * Indica se existe pelo menos mais um veículo por percorrer,
     * avançando internamente para o próximo grupo não vazio se necessário.
     *
     * @return {@code true} se houver mais veículos, {@code false} caso contrário
     */
    @Override
    public boolean hasNext() {
        while ((atual == null || !atual.hasNext()) && grupos.hasNext()) {
            atual = grupos.next().iterator();
        }
        return atual != null && atual.hasNext();
    }

    /**
     * Devolve o próximo veículo da garagem.
     *
     * @return o próximo {@link Veiculo}
     * @throws NoSuchElementException se não houver mais veículos a percorrer
     */
    @Override
    public Veiculo next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return atual.next();
    }
}
