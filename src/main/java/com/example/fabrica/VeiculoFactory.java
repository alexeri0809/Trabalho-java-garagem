package com.example.fabrica;

import com.example.veiculo.Veiculo;

/**
 * Padrão <b>Factory Method</b>: classe criadora abstrata. Cada subclasse
 * ({@link CarroFactory}, {@link BarcoFactory}, {@link MotaFactory}) decide,
 * através do método {@link #criarVeiculo(String, String)}, que tipo concreto
 * de {@link Veiculo} é instanciado.
 */
public abstract class VeiculoFactory {

    /**
     * Cria um veículo com a marca e o modelo dados, delegando o tipo concreto
     * na subclasse através do método fábrica {@link #criarVeiculo(String, String)}.
     *
     * @param marca  marca do veículo (espaços em branco extra são removidos)
     * @param modelo modelo do veículo (espaços em branco extra são removidos)
     * @return o veículo criado
     */
    public Veiculo criar(String marca, String modelo) {
        return criarVeiculo(marca.trim(), modelo.trim());
    }

    /**
     * Método fábrica a implementar por cada subclasse, responsável por
     * instanciar o tipo concreto de veículo.
     *
     * @param marca  marca do veículo, já sem espaços extra
     * @param modelo modelo do veículo, já sem espaços extra
     * @return o veículo concreto criado
     */
    protected abstract Veiculo criarVeiculo(String marca, String modelo);
}
