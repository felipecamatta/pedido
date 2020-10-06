package br.ufes;

import br.ufes.model.Cliente;
import br.ufes.model.CarrinhoDeCompra;
import br.ufes.model.Produto;
import br.ufes.model.TipoProduto;
import java.time.LocalDate;

public class Principal {

    public static void main(String[] args) {

        try {

            Cliente cliente1 = new Cliente("Fulano", "123.456.789-01");

            Produto produto1 = new Produto("Caneta Bic", 3.50, 8, new TipoProduto(0.05, "caneta"));

            CarrinhoDeCompra pedido1 = new CarrinhoDeCompra(cliente1,
                    produto1, 5,
                    LocalDate.now()
            );

            pedido1.addItem(new Produto("Folha Papel A4", 0.05, 10, new TipoProduto(0.1, "Folha")), 10);

            System.out.println(pedido1);

        } catch (RuntimeException rte) {
            System.err.println("Falha: " + rte.getMessage());

        }

    }

}
