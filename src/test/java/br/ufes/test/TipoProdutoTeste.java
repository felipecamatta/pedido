/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.test;

import br.ufes.model.TipoProduto;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 55289
 */
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
    public void CT001(){
        
        String tipoEsperado = "eletro";
        double percEsperado = 0.15;
        
        TipoProduto tipo1 = new TipoProduto(percEsperado, tipoEsperado);
        
        //SAIDA ESPERADA
        TipoProduto tipo2  = new TipoProduto(0.15,"eletro");
        
        //VERIFICANDO SAÍDA
        assertEquals(tipo1.getPercDesconto(),tipo2.getPercDesconto(),0000.1);
        assertEquals(tipo1.getTipo(),tipo2.getTipo());
       
    }
}
