package br.ufes.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.ufes.model.Boleto;
import br.ufes.model.CarrinhoDeCompra;
import br.ufes.model.Cliente;
import br.ufes.model.Item;
import br.ufes.model.Produto;
import br.ufes.model.TipoProduto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

/**
 *
 * @author 55289
 */
public class PedidoTeste {

    public PedidoTeste() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void CT01(){
        Cliente cliente1 = new Cliente("Fulano", "123.456.789-01");
        CarrinhoDeCompra carrinho = new CarrinhoDeCompra(cliente1, new Produto("Caderno HotWheels", 3.50, 20, new TipoProduto(0.05, "caderno")), 5, LocalDate.now());
        
    }
    
}
