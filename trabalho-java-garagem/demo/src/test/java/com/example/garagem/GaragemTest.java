package com.example.garagem;

import com.example.veiculo.Barco;
import com.example.veiculo.Carro;
import com.example.veiculo.Mota;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GaragemTest {

    @Test
    void comecaVaziaComCincoLugaresLivres() {
        Garagem garagem = new Garagem();
        assertEquals(0, garagem.getLugaresOcupados());
        assertEquals(5, garagem.getLugaresLivres());
    }

    @Test
    void naoDeixaPassarDeCincoVeiculos() {
        Garagem garagem = new Garagem();
        for (int i = 0; i < 5; i++) {
            assertTrue(garagem.adicionarVeiculo(new Carro("Marca" + i, "Modelo" + i)));
        }
        // O 6º veículo deve ser recusado
        assertFalse(garagem.adicionarVeiculo(new Carro("Extra", "Extra")));
        assertEquals(5, garagem.getLugaresOcupados());
        assertEquals(0, garagem.getLugaresLivres());
    }

    @Test
    void aceitaQualquerCombinacaoDeTipos() {
        Garagem garagem = new Garagem();
        garagem.adicionarVeiculo(new Carro("Toyota", "Corolla"));
        garagem.adicionarVeiculo(new Barco("Quicksilver", "Activ"));
        garagem.adicionarVeiculo(new Mota("Yamaha", "MT-07"));

        assertEquals(3, garagem.getLugaresOcupados());
    }
}
