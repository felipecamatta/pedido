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

    @AfterEach
    public void tearDown() {
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test // TESTANDO A FUNÇÃO "addItem"
    public void CT01() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate dt1 = LocalDate.parse("15/03/2020", formatter);

        Produto p1 = new Produto("smartphone", 200.00, 200,
                new TipoProduto("Eletrônico", 0.1));
        Produto p2 = new Produto("cafeteira", 100.00, 300,
                new TipoProduto("Eletrodoméstico", 0.2));

        ArrayList<Item> itens = new ArrayList<>();
        itens.add(new Item(p1, 5));
        itens.add(new Item(p2, 10));

        CarrinhoDeCompra carrinho = new CarrinhoDeCompra(new Cliente("Carlos", "1552"), p1, 5, dt1);
        carrinho.addItem(p2, 10);

        assertEquals(carrinho.getItens().toString(), itens.toString());
    }

    @Test // TESTANDO A FUNÇÃO "getItemPorNome"
    public void CT02() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate dt1 = LocalDate.parse("15/03/2020", formatter);

        Produto p1 = new Produto("smartphone", 200.00, 200,
                new TipoProduto("Eletrônico", 0.01));
        Produto p2 = new Produto("cafeteira", 100.00, 300,
                new TipoProduto("Eletrodoméstico", 0.02));

        CarrinhoDeCompra carrinho = new CarrinhoDeCompra(new Cliente("Carlos", "1552"), p1, 5, dt1);
        carrinho.addItem(p2, 10);

        assertEquals(carrinho.getItemPorNomeProduto("cafeteira").getProduto().toString(), p2.toString());
    }

    @Test // TESTANDO A FUNÇÃO "alterarQuantidade"
    public void CT03() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate dt1 = LocalDate.parse("15/03/2020", formatter);

        Produto p1 = new Produto("smartphone", 200.00, 200,
                new TipoProduto("Eletrônico", 0.01));

        CarrinhoDeCompra carrinho = new CarrinhoDeCompra(new Cliente("Carlos", "1552"), p1, 5, dt1);

        exception.expect(RuntimeException.class);

        carrinho.alterarQuantidade(p1, 3000);

        double qtdEsperada = 3000;

        assertEquals(qtdEsperada, carrinho.getItemPorNomeProduto("smartphone").getQuantidade());
    }

    @Test // TESTANDO A FUNÇÂO "removerItem"
    public void CT04() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate dt1 = LocalDate.parse("15/03/2020", formatter);

        Produto p1 = new Produto("smartphone", 200.00, 200,
                new TipoProduto("Eletrônico", 0.01));
        Produto p2 = new Produto("cafeteira", 100.00, 300,
                new TipoProduto("Eletrodoméstico", 0.02));

        //CRIANDO UMA LISTA DE ITENS E ADICIONANDO OS 2 PRODUTOS
        List<Item> itens = new ArrayList<>();
        itens.add(new Item(p1, 5));

        //SIMULANDO A ENTRADA DOS 2 ITENS NO CARRINHO
        CarrinhoDeCompra carrinho = new CarrinhoDeCompra(new Cliente("Carlos", "1552"), p1, 5, dt1); //ADICIONANDO NO CARRINHO
        carrinho.addItem(p2, 10);

        //REMOVENDO A "cafeteira"
        carrinho.removerItem(p2);

        assertEquals(carrinho.getItens().toString(), itens.toString());
    }

}
