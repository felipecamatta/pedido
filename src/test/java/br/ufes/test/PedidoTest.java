package br.ufes.test;

import br.ufes.enumeracoes.FormaPagamento;
import br.ufes.enumeracoes.UF;
import br.ufes.interfaces.IPoliticaDeDesconto;
import br.ufes.interfaces.implementacao.DescontoPorPontuacao;
import br.ufes.interfaces.implementacao.DescontoPorTipoProduto;
import br.ufes.model.CarrinhoDeCompra;
import br.ufes.model.Cliente;
import br.ufes.model.Endereco;
import br.ufes.model.Pedido;
import br.ufes.model.Produto;
import br.ufes.model.TipoProduto;
import java.time.LocalDate;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.rules.ExpectedException;

public class PedidoTest {

    public PedidoTest() {
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

    @Test
    //Aplicando 2x o mesmo tipo de desconto
    public void CT01() {
        Cliente cliente1 = new Cliente("Fulano", "123.456.789-01");
        Endereco enderecoCliente = new Endereco("Rua Maria Deta Lond", "Casa", "30622-490", "Bonsucesso (Barreiro)", "Belo Horizonte", 247, UF.MG);
        cliente1.setEndereco(enderecoCliente);
        Endereco enderecoLoja = new Endereco("Rua Dr. Wanderley", "Prédio", "29500-000", "Centro", "Alegre", 20, UF.ES);

        CarrinhoDeCompra carrinho = new CarrinhoDeCompra(cliente1, new Produto("Caderno HotWheels", 3.50, 8, new TipoProduto("caderno", 0.05)), 5, LocalDate.now());
        Pedido p = new Pedido(carrinho, enderecoLoja, FormaPagamento.BOLETO);

        IPoliticaDeDesconto desc1 = new DescontoPorPontuacao();
        IPoliticaDeDesconto desc2 = new DescontoPorTipoProduto();
        IPoliticaDeDesconto desc3 = new DescontoPorPontuacao();

        p.aplicarDesconto(desc1);
        p.aplicarDesconto(desc2);
        exception.expect(RuntimeException.class);
        p.aplicarDesconto(desc3);

    }

    @Test
    //Verificando o resultado de uma compra
    public void CT02() {
        Cliente cliente1 = new Cliente("Fulano", "123.456.789-01");
        Endereco enderecoCliente = new Endereco("Rua Maria Deta Lond", "Casa", "30622-490", "Bonsucesso (Barreiro)", "Belo Horizonte", 247, UF.MG);
        cliente1.setEndereco(enderecoCliente);
        cliente1.incrementarPontuacao(70);//Pontuação necessária para 15% de desconto
        Endereco enderecoLoja = new Endereco("Rua Dr. Wanderley", "Prédio", "29500-000", "Centro", "Alegre", 20, UF.ES);

        CarrinhoDeCompra carrinho = new CarrinhoDeCompra(cliente1, new Produto("Caderno HotWheels", 3.50, 8, new TipoProduto("caderno", 0.1)), 5, LocalDate.now());
        carrinho.addItem(new Produto("Tilibra", 2, 10, new TipoProduto("lápis", 0.05)), 3);
        Pedido p = new Pedido(carrinho, enderecoLoja, FormaPagamento.BOLETO);

        IPoliticaDeDesconto desc2 = new DescontoPorPontuacao();
        IPoliticaDeDesconto desc1 = new DescontoPorTipoProduto();//Desconto sobre o valor total do item, independente de descontos prévios

        p.aplicarDesconto(desc1);
        p.aplicarDesconto(desc2);

        //Pela ordem, valor unitário * quantidade * desc. Tipo do produto * desc. Pontuação 
        assertEquals((3.5 * 5 * 0.9 * 0.85) + (2 * 3 * 0.95 * 0.85), p.getValorComDesconto(), 0.001);
    }

    //Testes para validação do pedido
    @Test
    public void CT03() throws Exception {
        Cliente cliente1 = new Cliente("Fulano", "123.456.789-01");
        Endereco enderecoCliente = new Endereco("Rua Maria Deta Lond", "Casa", "30622-490", "Bonsucesso (Barreiro)", "Belo Horizonte", 247, UF.MG);
        cliente1.setEndereco(enderecoCliente);
        cliente1.incrementarPontuacao(70);//Pontuação necessária para 15% de desconto
        Endereco enderecoLoja = new Endereco("Rua Dr. Wanderley", "Prédio", "29500-000", "Centro", "Alegre", 20, UF.ES);

        CarrinhoDeCompra carrinho = new CarrinhoDeCompra(cliente1, new Produto("Caderno HotWheels", 3.50, 8, new TipoProduto("caderno", 0.1)), 5, LocalDate.now());
        carrinho.addItem(new Produto("Tilibra", 2, 10, new TipoProduto("lápis", 0.05)), 3);
        Pedido p = new Pedido(carrinho, enderecoLoja, FormaPagamento.BOLETO);

        p.setData(LocalDate.now().plusDays(-6));
        exception.expect(RuntimeException.class);

        p.concluir();//Não há necessidade de um try-catch, o próprio teste espera isso
    }

    @Test
    public void CT04() throws Exception {
        Cliente cliente1 = new Cliente("Fulano", "123.456.789-01");
        Endereco enderecoCliente = new Endereco("Rua Maria Deta Lond", "Casa", "30622-490", "Bonsucesso (Barreiro)", "Belo Horizonte", 247, UF.MG);
        cliente1.setEndereco(enderecoCliente);
        cliente1.incrementarPontuacao(70);//Pontuação necessária para 15% de desconto
        Endereco enderecoLoja = new Endereco("Rua Dr. Wanderley", "Prédio", "29500-000", "Centro", "Alegre", 20, UF.ES);

        CarrinhoDeCompra carrinho = new CarrinhoDeCompra(cliente1, new Produto("Caderno HotWheels", 3.50, 8, new TipoProduto("caderno", 0.1)), 5, LocalDate.now());
        carrinho.addItem(new Produto("Tilibra", 2, 10, new TipoProduto("lápis", 0.05)), 3);
        Pedido p = new Pedido(carrinho, enderecoLoja, FormaPagamento.BOLETO);

        p.cancelar();
        exception.expect(RuntimeException.class);

        p.concluir();//Não há necessidade de um try-catch, o próprio teste espera isso
    }

}
