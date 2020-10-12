package br.ufes.test;

import br.ufes.model.CarrinhoDeCompra;
import br.ufes.model.Cliente;
import br.ufes.model.Item;
import br.ufes.model.Produto;
import br.ufes.model.TipoProduto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.rules.ExpectedException;

public class CarrinhoDeCompraTest {

    public CarrinhoDeCompraTest() {
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

    @AfterEach //ALGUMA SUGESTAO
    public void tearDown() {
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    //TESTANDO A FUNÇÃO "addItem"
    @Test
    public void CT01() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate dt1 = LocalDate.parse("15/03/2020", formatter);

        //SIMULANDO A CRIACAO DE 2 PRODUTOS
        String nome1 = "smartphone", nome2 = "cafeteira", tipo1 = "Eletrônico", tipo2 = "Eletrodoméstico";
        double valor1 = 200.00, valor2 = 100.00, desc1 = 0.1, desc2 = 0.2;
        double qtdEstoque1 = 200, qtdEstoque2 = 300;
        int qtdAdd1 = 5, qtdAdd2 = 10;

        Produto p1 = new Produto(nome1, valor1, qtdEstoque1,
                new TipoProduto(tipo1, desc1));
        Produto p2 = new Produto(nome2, valor2, qtdEstoque2,
                new TipoProduto(tipo2, desc2));

        //CRIANDO UMA LISTA DE ITENS E ADICIONANDO OS 2 PRODUTOS
        ArrayList<Item> itens = new ArrayList<>();
        itens.add(
                new Item(p1, qtdAdd1));
        itens.add(
                new Item(p2, qtdAdd2));

        //SIMULANDO A ENTRADA DOS 2 ITENS NO CARRINHO
        CarrinhoDeCompra carrinho = new CarrinhoDeCompra(new Cliente("Carlos", "1552"), p1, qtdAdd1, dt1);
        carrinho.addItem(p2, 10);

        //CONFERINDO SE OS 2 PRODUTOS CRIADOS EQUIVALEM AOS PRODUTOS DO CARRINHO
        assertEquals(carrinho.getItens().toString(), itens.toString());

    }

    //TESTANDO A FUNÇÃO "getItemPorNome"
    @Test
    public void CT02() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate dt1 = LocalDate.parse("15/03/2020", formatter);

        //SIMULANDO A CRIACAO DE 2 PRODUTOS
        String nome1 = "smartphone", nome2 = "cafeteira", tipo1 = "Eletrônico", tipo2 = "Eletrodoméstico";
        double valor1 = 200.00, valor2 = 100.00, desc1 = 0.01, desc2 = 0.02;
        double qtdEstoque1 = 200, qtdEstoque2 = 300;
        int qtdAdd1 = 5, qtdAdd2 = 10;

        Produto p1 = new Produto(nome1, valor1, qtdEstoque1,
                new TipoProduto(tipo1, desc1));
        Produto p2 = new Produto(nome2, valor2, qtdEstoque2,
                new TipoProduto(tipo2, desc2));

        //SIMULANDO A ENTRADA DOS 2 ITENS NO CARRINHO
        CarrinhoDeCompra carrinho = new CarrinhoDeCompra(new Cliente("Carlos", "1552"), p1, qtdAdd1, dt1);
        carrinho.addItem(p2, qtdAdd2);

        //CONFERINDO SE O ITEM BUSCADO POR NOME ESTÁ FUNCIONANDO
        System.out.println(p2.toString());
        assertEquals(carrinho.getItemPorNomeProduto(nome2).getProduto().toString(), p2.toString()); //felipe olhar
    }

    //TESTANDO A FUNÇÃO "alterarQuantidade"
    @Test
    public void CT03() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate dt1 = LocalDate.parse("15/03/2020", formatter);

        //DADOS ESPERADOS PARA PRODUTO
        String nome1 = "smartphone", tipo1 = "Eletrônico";
        double valor1 = 200.00, desc1 = 0.01;
        double qtd1 = 200;

        Produto p1 = new Produto(nome1, valor1, qtd1,
                new TipoProduto(tipo1, desc1));

        //SIMULANDO A ENTRADA DE 1 ITEM NO CARRINHO
        CarrinhoDeCompra carrinho = new CarrinhoDeCompra(new Cliente("Carlos", "1552"), p1, 5, dt1);

        exception.expect(RuntimeException.class);

        //SIMULANDO A ALTERAÇÃO DA QUANTIDADE DO PRODUTO
        carrinho.alterarQuantidade(p1, 3000);

        //QUANTIDADE PRODUTO ESPERADO
        double qtdEsperada = 3000;

        //CONFERINDO SE A ALTERAÇÃO OCORREU CORRETAMENTE
        assertEquals(qtdEsperada, carrinho.getItemPorNomeProduto(nome1).getQuantidade());
        //especificar a quantidade, pois existe a quantidade para a compra, e a quantidade do produto em estoque!
    }

    //TESTANDO A FUNÇÃO "calcularValor"
    /*@Test
    public void CT004() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate dt1 = LocalDate.parse("15/03/2020", formatter);

        //SIMULANDO A CRIACAO DE 2 PRODUTOS
        String nome1 = "smartphone", nome2 = "cafeteira", tipo1 = "Eletrônico", tipo2 = "Eletrodoméstico";
        double valor1 = 200.00, valor2 = 100.00, desc1 = 0.1, desc2 = 0.2;
        double qtd1 = 200, qtd2 = 300;
        int qtdAdd1 = 5, qtdAdd2 = 10;

        Produto p1 = new Produto(nome1, valor1, qtd1, 
                new TipoProduto(desc1, tipo1));
        Produto p2 = new Produto(nome2, valor2, qtd2,
                new TipoProduto(desc2, tipo2));

        //SIMULANDO A ENTRADA DOS 2 ITENS NO CARRINHO
        CarrinhoDeCompra carrinho= new CarrinhoDeCompra(new Cliente("Carlos", "1552"),p1, qtdAdd1, dt1); //ADICIONANDO NO CARRINHO
        carrinho.addItem(p2, qtdAdd2);// 

        //CONFERINDO SE O VALOR CALCULADO CORRESPONDE
        double valorEsperado = (((200*qtdAdd1)* (1-desc1)) + ((100*qtdAdd2)*(1-desc2))) * (1-carrinho.getDesconto());
        assertEquals(valorEsperado, carrinho.getValorAPagar()); 
    }*/
    //TESTANDO A FUNÇÃO "removerItem"
    @Test
    public void CT04() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate dt1 = LocalDate.parse("15/03/2020", formatter);

        //SIMULANDO A CRIACAO DE 2 PRODUTOS
        String nome1 = "smartphone", nome2 = "cafeteira", tipo1 = "Eletrônico", tipo2 = "Eletrodoméstico";
        double valor1 = 200.00, valor2 = 100.00, desc1 = 0.01, desc2 = 0.02;
        double qtdEstoque1 = 200, qtdEstoque2 = 300;
        int qtdAdd1 = 5, qtdAdd2 = 10;

        Produto p1 = new Produto(nome1, valor1, qtdEstoque1,
                new TipoProduto(tipo1, desc1));
        Produto p2 = new Produto(nome2, valor2, qtdEstoque2,
                new TipoProduto(tipo2, desc2));

        //CRIANDO UMA LISTA DE ITENS E ADICIONANDO OS 2 PRODUTOS
        List<Item> itens = new ArrayList<>();
        itens.add(new Item(p1, qtdAdd1));

        //SIMULANDO A ENTRADA DOS 2 ITENS NO CARRINHO
        CarrinhoDeCompra carrinho = new CarrinhoDeCompra(new Cliente("Carlos", "1552"), p1, qtdAdd1, dt1); //ADICIONANDO NO CARRINHO
        carrinho.addItem(p2, qtdAdd2);

        //REMOVENDO A "cafeteira"
        carrinho.removerItem(p2);

        //CONFERINDO SE A REMOÇÃO FOI CONCLUÍDA CORRETAMENTE
        assertEquals(carrinho.getItens().toString(), itens.toString());

    }

}
