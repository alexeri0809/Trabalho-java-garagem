package com.example.facade;

import com.example.veiculo.TipoVeiculo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GaragemFacadeTest {

    @Test
    void facadeRespeitaLimiteDeCincoLugares() {
        GaragemFacade garagem = new GaragemFacade();

        for (int i = 0; i < 5; i++) {
            assertTrue(garagem.adicionarVeiculo(TipoVeiculo.CARRO, "Marca", "Modelo"));
        }
        assertFalse(garagem.adicionarVeiculo(TipoVeiculo.MOTA, "Extra", "Extra"));
        assertEquals(0, garagem.getLugaresLivres());
    }
}
