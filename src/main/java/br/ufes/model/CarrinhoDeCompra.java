package br.ufes.model;

import br.ufes.interfaces.IFormaPagamento;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

public final class CarrinhoDeCompra {

    private Cliente cliente;
    private double valor;
    private final double desconto = 0.05;
    private double valorDesconto;
    private double valorAPagar;
    private ArrayList<Item> itens;
    private final LocalDate data;
    private final LocalDate dataVencimento;

    public CarrinhoDeCompra(Cliente cliente, Produto produto, double quantidade, LocalDate data) {
        if (cliente == null) {
            throw new RuntimeException("Informe um cliente válido");
        }
        this.cliente = cliente;
        this.data = data;
        this.dataVencimento = data.plusMonths(1);
        this.addItem(produto, quantidade);
        this.itens = new ArrayList<Item>();
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

    public Item getItemPorNomeProduto(String nomeProduto) {
        for (Item item : itens) {
            if (item.getProduto().getNome().toLowerCase().equals(nomeProduto.toLowerCase())) {
                return item;
            }
        }
        return null;
    }

    public Pedido fechar(CarrinhoDeCompra carrinhoDeCompra, IFormaPagamento formaPagamento) {
        Pedido pedido = new Pedido(
                LocalDate.now(),
                carrinhoDeCompra.getValor(),
                LocalDate.now().plusDays(5),
                carrinhoDeCompra,
                formaPagamento
        );
        return pedido;
    }

    private void calcularValor() {
        valor = 0;
        for (Item item : itens) {
            valor += item.getValorItem();
        }
        aplicarDesconto();
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    private void aplicarDesconto() {
        this.valorDesconto = valor * desconto;
        this.valorAPagar = valor - valorDesconto;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public double getValor() {
        return valor;
    }

    public double getDesconto() {
        return desconto;
    }

    public double getValorAPagar() {
        return valorAPagar;
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
        retorno += "Data: " + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", ";
        retorno += "Data de vencimento: " + dataVencimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n";
        retorno += "Valor sem desconto: R$ " + df.format(getValor()) + "\n";
        retorno += "Desconto: R$: " + df.format(valorDesconto) + " (" + desconto * 100 + "%)\n";
        retorno += "Valor a pagar: R$ " + df.format(valorAPagar) + "\n";
        retorno += "Itens do pedido:\n";
        for (Item item : itens) {
            retorno += "\t- " + item.toString() + "\n";
        }
        return retorno;
    }

}
