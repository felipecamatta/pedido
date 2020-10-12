package br.ufes.test;

import br.ufes.model.Cliente;
import java.time.LocalDate;
import java.time.Month;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClienteTest {

    public ClienteTest() {
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
    
    // TESTANDO "diminuirPontuacao" para pontuação do cliente superior à pontuação a ser diminuída
    @Test
    public void CT003() {
        Cliente cliente = new Cliente("Lucas", "123.456.789-13", null, LocalDate.of(1990, Month.MARCH, 27), 15.0);
        
        double pontuacaoDiminuir = 5.0;
        double pontuacaoEsperada = 10.0;
        
        cliente.diminuirPontuacao(pontuacaoDiminuir);
        
        assertEquals(pontuacaoEsperada, cliente.getPontuacao(), 0.01);
    }
    
    // TESTANDO "diminuirPontuacao" para pontuação do cliente igual à pontuação a ser diminuída
    @Test
    public void CT004() {
        Cliente cliente = new Cliente("Lucas", "123.456.789-13", null, LocalDate.of(1990, Month.MARCH, 27), 15.0);
        
        double pontuacaoDiminuir = 15.0;
        double pontuacaoEsperada = 0.0;
        
        cliente.diminuirPontuacao(pontuacaoDiminuir);
        
        assertEquals(pontuacaoEsperada, cliente.getPontuacao(), 0.01);
    }
    
    // TESTANDO "diminuirPontuacao" para pontuação do cliente inferior à pontuação a ser diminuída
    @Test
    public void CT005() {
        Cliente cliente = new Cliente("Lucas", "123.456.789-13", null, LocalDate.of(1990, Month.MARCH, 27), 15.0);
        
        double pontuacaoDiminuir = 20.0;
        double pontuacaoEsperada = 0.0;
        
        cliente.diminuirPontuacao(pontuacaoDiminuir);
        
        assertEquals(pontuacaoEsperada, cliente.getPontuacao(), 0.01);
    }
    
}
