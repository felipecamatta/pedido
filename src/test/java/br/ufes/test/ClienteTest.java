package br.ufes.test;

import br.ufes.model.Cliente;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClienteTeste {

    public ClienteTeste() {
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

    @Test // TESTANDO "toString"
    public void CT001() {
        String nome = "Lucas", CPF = "123.456.789-13";
        Cliente cliente = new Cliente(nome, CPF);

        //toString ESPERADO
        String toStringEsperado = "Cliente: Lucas, CNPJ/CPF = 123.456.789-13";

        //VERIFICANDO A SAÍDA
        assertEquals(cliente.toString(), toStringEsperado);
    }

    //TESTANDO "incrementarPontuacao"
    //REVISAR
    @Test
    public void CT002() {
        String nome = "Lucas", CPF = "123.456.789-13";
        Cliente cliente = new Cliente(nome, CPF);
        //cliente.setPontuacao(0);
        double pontuacao = 20.0;
        cliente.incrementarPontuacao(pontuacao);

        //VERIFICANDO A SAIDA
        assertEquals(cliente.getPontuacao(), pontuacao, 000.1);
    }
}
