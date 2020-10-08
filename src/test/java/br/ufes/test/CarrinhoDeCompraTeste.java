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
import java.util.Optional;
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

    //TESTANDO A FUNÇÃO "addItem"
    @Test
    public void CT001() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate dt1 = LocalDate.parse("15/03/2020", formatter);

        //SIMULANDO A CRIACAO DE 2 PRODUTOS
        String nome1 = "smartphone", nome2 = "cafeteira", tipo1 = "Eletrônico", tipo2 = "Eletrodoméstico";
        double valor1 = 200.00, valor2 = 100.00, desc1 = 0.01, desc2 = 0.02;
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

        //SIMULANDO A ENTRADA DOS 2 ITENS NO CARRINHO
        CarrinhoDeCompra carrinho
                = new CarrinhoDeCompra(
                        new Cliente("Carlos", "1552"),
                        new Produto("smartphone", 200.00, 200,
                                new TipoProduto(0.01, "Eletrônico")), 5, dt1);
        carrinho.addItem(
                new Produto("cafeteira", 100.00, 300,
                        new TipoProduto(0.02, "Eletrodoméstico")), 10);

        //CONFERINDO SE OS 2 PRODUTOS CRIADOS EQUIVALEM AOS PRODUTOS DO CARRINHO
        assertEquals(carrinho.getItens(), itens); 
        
    }

    //TESTANDO A FUNÇÃO "getItemPorNome"
    @Test
    public void CT002() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate dt1 = LocalDate.parse("15/03/2020", formatter);

        //SIMULANDO A CRIACAO DE 2 PRODUTOS
        String nome1 = "smartphone", nome2 = "cafeteira", tipo1 = "Eletrônico", tipo2 = "Eletrodoméstico";
        double valor1 = 200.00, valor2 = 100.00, desc1 = 0.01, desc2 = 0.02;
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

        //SIMULANDO A ENTRADA DOS 2 ITENS NO CARRINHO
        CarrinhoDeCompra carrinho
                = new CarrinhoDeCompra(
                        new Cliente("Carlos", "1552"),
                        new Produto("smartphone", 200.00, 200,
                                new TipoProduto(0.01, "Eletrônico")), 5, dt1);
        carrinho.addItem(
                new Produto("cafeteira", 100.00, 300,
                        new TipoProduto(0.02, "Eletrodoméstico")), 10);

        //CONFERINDO SE O ITEM BUSCADO POR NOME ESTÁ FUNCIONANDO
        //assertEquals(carrinho.get,itens); //felipe olhar
    }

    //TESTANDO A FUNÇÃO "quantidadeProduto"
    @Test
    public void CT003() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate dt1 = LocalDate.parse("15/03/2020", formatter);

        //DADOS ESPERADOS PARA PRODUTO
        String nome1 = "smartphone", tipo1 = "Eletrônico";
        double valor1 = 200.00, desc1 = 0.01;
        double qtd1 = 200;

        Produto p1 = new Produto(nome1, valor1, qtd1,
                new TipoProduto(desc1, tipo1));

        //CRIANDO ITEM, A PARTIR DO PRODUTO, PARA ADICIONAR NO CARRINHO
        double qtdAd = 5;
        Item i1 = new Item(p1, qtdAd);

        //SIMULANDO A ENTRADA DE 1 ITEM NO CARRINHO
        CarrinhoDeCompra carrinho
                = new CarrinhoDeCompra(
                        new Cliente("Carlos", "1552"), p1, 5, dt1);

        //SIMULANDO A ALTERAÇÃO DA QUANTIDADE DO PRODUTO
        carrinho.quantidadeProduto(i1, 3000);

        //QUANTIDADE PRODUTO ESPERADO
        double qtdEsperada = 3000;

        //CONFERINDO SE A ALTERAÇÃO OCORREU CORRETAMENTE
        //assertEquals(qtdEsperada, carrinho.getItemPorNome(nome1));
    }

    //TESTANDO A FUNÇÃO "calcularValor"
    @Test
    public void CT004() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate dt1 = LocalDate.parse("15/03/2020", formatter);

        //SIMULANDO A CRIACAO DE 2 PRODUTOS
        String nome1 = "smartphone", nome2 = "cafeteira", tipo1 = "Eletrônico", tipo2 = "Eletrodoméstico";
        double valor1 = 200.00, valor2 = 100.00, desc1 = 0.01, desc2 = 0.02;
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

        //SIMULANDO A ENTRADA DOS 2 ITENS NO CARRINHO
        CarrinhoDeCompra carrinho
                = new CarrinhoDeCompra(
                        new Cliente("Carlos", "1552"),
                        new Produto("smartphone", 200.00, 200,
                                new TipoProduto(0.01, "Eletrônico")), 5, dt1);
        carrinho.addItem(
                new Produto("cafeteira", 100.00, 300,
                        new TipoProduto(0.02, "Eletrodoméstico")), 10);

        //CONFERINDO SE O VALOR CALCULADO CORRESPONDE
        double valorEsperado = ((qtdAdd1 * valor1) * (1 + desc1)) + ((qtdAdd2 * valor2) * (1 + desc2));
        assertEquals(valorEsperado, carrinho.getValor());
    }

    //TESTANDO A FUNÇÃO "removerItem"
    @Test
    public void CT005() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate dt1 = LocalDate.parse("15/03/2020", formatter);

        //SIMULANDO A CRIACAO DE 2 PRODUTOS
        String nome1 = "smartphone", nome2 = "cafeteira", tipo1 = "Eletrônico", tipo2 = "Eletrodoméstico";
        double valor1 = 200.00, valor2 = 100.00, desc1 = 0.01, desc2 = 0.02;
        double qtd1 = 200, qtd2 = 300;
        int qtdAdd1 = 5, qtdAdd2 = 10;

        Produto p1 = new Produto(nome1, valor1, qtd1,
                new TipoProduto(desc1, tipo1));
        Produto p2 = new Produto(nome2, valor2, qtd2,
                new TipoProduto(desc2, tipo2));

        //CRIANDO UMA LISTA DE ITENS E ADICIONANDO OS 2 PRODUTOS
        ArrayList<Item> itens = new ArrayList<>();
        itens.add(
                new Item(p1, qtdAdd1));
        itens.add(
                new Item(p2, qtdAdd2));

        //SIMULANDO A ENTRADA DOS 2 ITENS NO CARRINHO
        CarrinhoDeCompra carrinho
                = new CarrinhoDeCompra(
                        new Cliente("Carlos", "1552"),
                        new Produto("smartphone", 200.00, 200,
                                new TipoProduto(0.01, "Eletrônico")), 5, dt1);
        carrinho.addItem(
                new Produto("cafeteira", 100.00, 300,
                        new TipoProduto(0.02, "Eletrodoméstico")), 10);

        //REMOVENDO A "cafeteira"
        carrinho.removerItem(new Item(p2, qtdAdd2));

        //CONFERINDO SE A REMOÇÃO FOI CONCLUÍDA CORRETAMENTE
        assertEquals(carrinho.getItens(), new Item(p1, qtdAdd1));
    }

}
