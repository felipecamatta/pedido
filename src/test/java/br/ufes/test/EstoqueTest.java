package br.ufes.test;

import br.ufes.model.Estoque;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class EstoqueTest {

    public EstoqueTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void CT001() {
        double quantidadeEsperada = 300.0;
        Estoque estoque = new Estoque(quantidadeEsperada);

        //CONFERINDO SAIDA
        assertEquals(estoque.getQuantidade(), quantidadeEsperada, 0000.1);

    }

    //TESTANDO FUNÇÃO "aumentarQuantidade"
    @Test
    public void CT002() {
        double quantidadeInicio = 300.0;
        double quantidadeAum = 200.0;
        Estoque estoque = new Estoque(quantidadeInicio);
        estoque.aumentarQuantidade(quantidadeAum);

        double quantidadeEsperada = 500.0;

        //CONFERINDO SAIDA
        assertEquals(estoque.getQuantidade(), quantidadeEsperada, 0000.1);
    }

    //TESTANDO FUNÇÃO "diminuirQuantidade"
    @Test
    public void CT003() {
        double quantidadeInicio = 300.0;
        double quantidadeDim = 200.0;
        Estoque estoque = new Estoque(quantidadeInicio);
        estoque.diminuirQuantidade(quantidadeDim);

        double quantidadeEsperada = 100.0;

        //CONFERINDO SAIDA
        assertEquals(estoque.getQuantidade(), quantidadeEsperada, 0000.1);
    }
}
