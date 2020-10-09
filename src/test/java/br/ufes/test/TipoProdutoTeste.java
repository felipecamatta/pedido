package br.ufes.test;

import br.ufes.model.TipoProduto;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TipoProdutoTeste {

    public TipoProdutoTeste() {
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
        String tipoEsperado = "eletro";
        double percEsperado = 0.15;

        TipoProduto tipo1 = new TipoProduto(tipoEsperado, percEsperado);

        //SAIDA ESPERADA
        TipoProduto tipo2 = new TipoProduto("eletro", 0.15);

        //VERIFICANDO SAÍDA
        assertEquals(tipo1.getDesconto(), tipo2.getDesconto(), 0000.1);
        assertEquals(tipo1.getTipo(), tipo2.getTipo());
    }

}
