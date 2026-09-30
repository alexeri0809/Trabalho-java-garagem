package com.example.matricula;

/**
 * Padrão <b>Strategy</b>: define o contrato comum a todas as estratégias
 * de geração de matrícula. Cada tipo de veículo (terrestre ou marítimo)
 * usa uma implementação diferente desta interface.
 */
public interface EstrategiaMatricula {

    /**
     * Gera uma matrícula aleatória, no formato próprio da estratégia.
     *
     * @return a matrícula gerada
     */
    String gerar();
}
