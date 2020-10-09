package br.ufes.model;

import br.ufes.enumeracoes.FormaPagamento;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public final class CarrinhoDeCompra {

    private Cliente cliente;
    private List<Item> itens;

    public CarrinhoDeCompra(Cliente cliente, Produto produto, double quantidade, LocalDate data) {
        if (cliente == null) {
            throw new RuntimeException("Informe um cliente válido");
        }
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.addItem(produto, quantidade);
    }

    public final void addItem(Produto produto, double quantidade) {
        if (quantidade <= 0) {
            throw new RuntimeException("Informe uma quantidade válida!");
        }
        if (this.getItemPorNomeProduto(produto.getNome()) != null) {
            throw new RuntimeException("Produto já existe! Remova-o ou altere a quantidade");
        }
        itens.add(new Item(produto, quantidade));
        calcularValor();
    }

    public void removerItem(Produto produto) {
        Item produtoEncontrado = getItemPorNomeProduto(produto.getNome());
        if (produtoEncontrado == null) {
            throw new RuntimeException("Item " + produto.getNome() + " não encontrado");
        }
        itens.remove(produtoEncontrado);
        calcularValor();
    }

    public void alterarQuantidade(Produto produto, int quantidade) {
        if (quantidade == 0) {
            removerItem(produto);
        } else if (quantidade > 0) {
            Item item = getItemPorNomeProduto(produto.getNome());
            item.setQuantidade(quantidade);
        }
    }

    public Item getItemPorNomeProduto(String nomeProduto) {
        for (Item item : itens) {
            if (item.getProduto().getNome().toLowerCase().equals(nomeProduto.toLowerCase())) {
                return item;
            }
        }
        return null;
    }

    public Pedido fechar(Endereco enderecoOrigem, FormaPagamento formaPagamento) {
        Pedido pedido = new Pedido(this, enderecoOrigem, formaPagamento);
        return pedido;
    }

    public double calcularValor() {
        double valor = 0;

        for (Item item : itens) {
            valor += item.getValorItem();
        }

        return valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Item> getItens() {
        return Collections.unmodifiableList(itens);
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        String retorno = "--------------- Pedido --------------\n";
        retorno += cliente + "\n";
        retorno += "Itens do pedido:\n";
        for (Item item : itens) {
            retorno += "\t- " + item.toString() + "\n";
        }
        return retorno;
    }

}
