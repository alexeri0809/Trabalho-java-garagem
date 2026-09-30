package com.example.garagem;

import com.example.veiculo.ElementoGaragem;
import com.example.veiculo.TipoVeiculo;
import com.example.veiculo.Veiculo;

import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Raiz do padrão <b>Composite</b> (contém os {@link GrupoVeiculos}) e também
 * {@code Iterable} (padrão <b>Iterator</b>, através de {@link IteradorGaragem}),
 * o que permite usar {@code for (Veiculo v : garagem)}.
 *
 * <p>Capacidade total fixa em {@value #CAPACIDADE_MAXIMA} lugares,
 * independentemente do tipo de veículo estacionado.</p>
 */
public class Garagem implements ElementoGaragem, Iterable<Veiculo> {
    /** Número máximo de veículos que a garagem pode conter, seja qual for o tipo. */
    private static final int CAPACIDADE_MAXIMA = 5;

    // EnumMap mantém a ordem do enum: Carro -> Barco -> Mota
    private final Map<TipoVeiculo, GrupoVeiculos> grupos = new EnumMap<>(TipoVeiculo.class);

    /**
     * Cria uma garagem vazia, com um {@link GrupoVeiculos} pronto para
     * cada {@link TipoVeiculo} existente.
     */
    public Garagem() {
        for (TipoVeiculo tipo : TipoVeiculo.values()) {
            grupos.put(tipo, new GrupoVeiculos(tipo.getNomePlural()));
        }
    }

    /**
     * Tenta adicionar um veículo à garagem, no grupo correspondente ao seu tipo.
     *
     * @param veiculo veículo a adicionar
     * @return {@code true} se foi adicionado, {@code false} se a garagem já estava cheia
     */
    public boolean adicionarVeiculo(Veiculo veiculo) {
        if (getLugaresLivres() == 0) {
            return false;
        }
        grupos.get(veiculo.getTipo()).adicionar(veiculo);
        return true;
    }

    /**
     * Remove um veículo da garagem, caso exista.
     *
     * @param veiculo veículo a remover
     * @return {@code true} se foi removido, {@code false} se não estava na garagem
     */
    public boolean removerVeiculo(Veiculo veiculo) {
        return grupos.get(veiculo.getTipo()).remover(veiculo);
    }

    /**
     * Devolve o número de lugares atualmente ocupados.
     *
     * @return número de veículos estacionados
     */
    public int getLugaresOcupados() {
        return contarVeiculos();
    }

    /**
     * Devolve o número de lugares ainda livres.
     *
     * @return número de lugares livres, entre 0 e {@value #CAPACIDADE_MAXIMA}
     */
    public int getLugaresLivres() {
        return CAPACIDADE_MAXIMA - contarVeiculos();
    }

    /**
     * {@inheritDoc}
     * <p>Soma os veículos de todos os grupos (Carros, Barcos e Motas).</p>
     */
    @Override
    public int contarVeiculos() {
        int total = 0;
        for (GrupoVeiculos grupo : grupos.values()) {
            total += grupo.contarVeiculos();
        }
        return total;
    }

    /**
     * {@inheritDoc}
     * <p>Mostra o total de ocupação da garagem seguido de cada grupo de veículos.</p>
     */
    @Override
    public void mostrar(String indentacao) {
        System.out.println(indentacao + "Garagem (" + contarVeiculos() + "/" + CAPACIDADE_MAXIMA + ")");
        for (GrupoVeiculos grupo : grupos.values()) {
            grupo.mostrar(indentacao + "  ");
        }
    }

    /**
     * Devolve um iterador (padrão Iterator) que percorre todos os veículos
     * da garagem, pela ordem Carro &rarr; Barco &rarr; Mota.
     *
     * @return iterador sobre todos os veículos da garagem
     */
    @Override
    public Iterator<Veiculo> iterator() {
        return new IteradorGaragem(grupos.values());
    }
}
