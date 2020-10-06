package br.ufes.model;

import br.ufes.enumeracoes.SituacaoPedido;
import br.ufes.interfaces.IFormaPagamento;
import br.ufes.interfaces.IPoliticaDeDesconto;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Pedido {

    private UUID codigo;
    private LocalDate data;
    private double desconto;
    private double valorTotal;
    private LocalDate dataValidade;
    private CarrinhoDeCompra carrinho;
    private Cliente cliente;
    private NotaFiscal notaFiscal;
    private List<IPoliticaDeDesconto> politicaDeDesconto;
    private IFormaPagamento formaPagamento;
    private SituacaoPedido situacao;

    public Pedido(LocalDate data,
            double valorTotal, LocalDate dataValidade,
            CarrinhoDeCompra carrinho, IFormaPagamento formaPagamento) {
        this.codigo = UUID.randomUUID();
        this.data = data;
        this.valorTotal = valorTotal;
        this.dataValidade = dataValidade;
        this.carrinho = carrinho;
        this.situacao = SituacaoPedido.PENDENTE;
        this.cliente = carrinho.getCliente();
        this.politicaDeDesconto = new ArrayList<IPoliticaDeDesconto>();
        this.formaPagamento = formaPagamento;
    }

    // TODO: Alterar para carrinho de compra
    public void removerItem(Produto produto) {
        carrinho.removerItem(produto);
    }

    // TODO: Alterar para carrinho de compra
    public void alterarQuantidade(Produto produto, int quantidade) {
        if (quantidade == 0) {
            removerItem(produto);
        } else if (quantidade > 0) {
            Item item = this.carrinho.getItemPorNomeProduto(produto.getNome());
            item.setQuantidade(quantidade);
        }
    }

    public void concluir(Pedido pedido) {
        if (LocalDate.now().isAfter(pedido.getDataValidade())) {
            pedido.setSituacao(SituacaoPedido.VENCIDO);
            throw new RuntimeException("Não foi possível concluir o pedido pois ele expirou");
        }

        removerProdutosDoPedidoDoEstoque(pedido);
    }

    private void removerProdutosDoPedidoDoEstoque(Pedido pedido) {
        pedido.getCarrinho().getItens().forEach(itemPedido -> {
            itemPedido.getProduto().getEstoque().diminuirQuantidade(itemPedido.getQuantidade());
        });
    }

    public UUID getCodigo() {
        return codigo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public CarrinhoDeCompra getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(CarrinhoDeCompra carrinho) {
        this.carrinho = carrinho;
    }

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public SituacaoPedido getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoPedido situacao) {
        this.situacao = situacao;
    }

}
