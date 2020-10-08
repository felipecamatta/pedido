/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.test;

import br.ufes.model.CarrinhoDeCompra;
import br.ufes.model.Cliente;
import br.ufes.model.Item;
import br.ufes.model.Produto;
import br.ufes.model.TipoProduto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author 55289
 */
public class CarrinhoDeCompraTeste {
    
    public CarrinhoDeCompraTeste() {
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

    //TESTANDO O ADICIONAR ITENS NO CARRINHO DE COMPRA
    public void CT001() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate dt1 = LocalDate.parse("15/03/2020", formatter);

        //SIMULANDO A CRIACAO DE 2 PRODUTOS
        String nome1 = "smartphone", nome2 = "cafeteira",tipo1 = "Eletrônico", tipo2 = "Eletrodoméstico";
        double valor1 = 200.00, valor2 = 100.00,desc1 = 0.01, desc2 = 0.02;
        double qtd1 = 200, qtd2 = 300;
        int qtdAdd1 = 5, qtdAdd2 = 10;
        
        Produto p1 = new Produto(nome1, valor1, qtd1, 
                     new TipoProduto(desc1, tipo1));
        Produto p2 = new Produto(nome2, valor2, qtd2, 
                     new TipoProduto(desc2, tipo2));

        //CRIANDO UMA LISTA DE ITENS E ADICIONANDO OS 2 PRODUTOS
        ArrayList<Item> itens = new ArrayList<>();
        itens.add(
                new Item(p2, qtdAdd1));
        itens.add(
                new Item(p1, qtdAdd2));

        //SIMULANDO A ENTRADA DOS 2 PRODUTOS NO CARRINHO
        CarrinhoDeCompra carrinho = 
                new CarrinhoDeCompra(
                new Cliente("Carlos", "1552"),
                new Produto("smartphone", 200.00, 200,
                new TipoProduto(0.01, "Eletrônico")), 5, dt1);
        carrinho.addItem(
                new Produto("cafeteira", 100.00, 300,
                new TipoProduto(0.02, "Eletrodoméstico")), 10);

        //CONFERINDO SE OS 2 PRODUTOS CRIADOS EQUIVALEM AOS PRODUTOS DO CARRINHO
        assertEquals(carrinho.getItens(),itens);
    }
}
