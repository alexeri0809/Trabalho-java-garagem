package com.example.veiculo;

/**
 * Componente comum do padrão <b>Composite</b>.
 *
 * <p>Tanto um {@link Veiculo} individual (folha) como um
 * {@link com.example.garagem.GrupoVeiculos} ou a
 * {@link com.example.garagem.Garagem} inteira (compostos) implementam
 * esta interface, permitindo tratá-los de forma uniforme ao contar
 * veículos ou ao mostrar o estado da garagem.</p>
 */
public interface ElementoGaragem {

    /**
     * Conta o número de veículos representados por este elemento.
     * Numa folha ({@link Veiculo}) é sempre 1; num composto, é a soma
     * dos veículos de todos os elementos que contém.
     *
     * @return número total de veículos
     */
    int contarVeiculos();

    /**
     * Imprime no ecrã a representação deste elemento (e dos seus
     * elementos filhos, se for um composto), com a indentação dada.
     *
     * @param indentacao espaço em branco a colocar antes do texto,
     *                    usado para representar os níveis da árvore
     */
    void mostrar(String indentacao);
}
