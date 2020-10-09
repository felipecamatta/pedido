package br.ufes.model;

import br.ufes.enumeracoes.FormaPagamento;
import br.ufes.enumeracoes.SituacaoPedido;
import br.ufes.interfaces.IPoliticaDeDesconto;
import br.ufes.util.ICMSUtil;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private Endereco enderecoOrigem;
    private Endereco enderecoDestino;
    private NotaFiscal notaFiscal;
    private List<IPoliticaDeDesconto> politicaDeDesconto;
    private SituacaoPedido situacao;
    private FormaPagamento formaPagamento;

    public Pedido(
            LocalDate data,
            double valorTotal,
            LocalDate dataValidade,
            CarrinhoDeCompra carrinho,
            Endereco enderecoOrigem,
            FormaPagamento formaPagamento
    ) {
        this.codigo = UUID.randomUUID();
        this.data = data;
        this.valorTotal = valorTotal;
        this.dataValidade = dataValidade;
        this.carrinho = carrinho;
        this.situacao = SituacaoPedido.PENDENTE;
        this.cliente = carrinho.getCliente();
        this.politicaDeDesconto = new ArrayList<IPoliticaDeDesconto>();
        this.enderecoOrigem = enderecoOrigem;
        this.enderecoDestino = this.cliente.getEndereco();
        this.formaPagamento = formaPagamento;
    }
    
    public Pedido(double valorTotal,
            CarrinhoDeCompra carrinho) {
        
        this.codigo = UUID.randomUUID();
        this.data = LocalDate.now();
        this.valorTotal = valorTotal;
        this.dataValidade = data.plusDays(5);
        this.carrinho = carrinho;
        this.situacao = SituacaoPedido.PENDENTE;
        this.cliente = carrinho.getCliente();
        this.politicaDeDesconto = new ArrayList<IPoliticaDeDesconto>();

    }
    
    public void aplicarDesconto(IPoliticaDeDesconto desconto){
        if(politicaDeDesconto.contains(desconto)){
            throw new RuntimeException("Desconto já aplicado!\n");
        }
        this.politicaDeDesconto.add(desconto);
    }
    
    private void calcularValorTotal(){
        this.valorTotal = this.carrinho.getValor();
        for(IPoliticaDeDesconto desc : politicaDeDesconto){
            this.desconto += desc.calcularDesconto(this.carrinho);
        }
    }

    public void concluir() throws Exception {
        this.calcularValorTotal();
        
        validarPedidoParaConcluir();
        setSituacao(SituacaoPedido.PAGO);
        removerProdutosDoPedidoDoEstoque();
        getCliente().incrementarPontuacao(getValorComDesconto() * 0.02);
        
        double taxaICMS = ICMSUtil.consultarTaxa(enderecoOrigem, enderecoDestino);
        
        setNotaFiscal(new NotaFiscal(taxaICMS, getValorTotal() * (taxaICMS / 100)));
    }

    public void cancelar() {
        this.calcularValorTotal();
        validarPedidoParaCancelar();
        setSituacao(SituacaoPedido.CANCELADO);
    }

    private void validarPedidoParaConcluir() {
        if (LocalDate.now().isAfter(getDataValidade())) {
            setSituacao(SituacaoPedido.VENCIDO);
            throw new RuntimeException("Não foi possível concluir o pedido pois ele expirou");
        }

        if (SituacaoPedido.CANCELADO.equals(getSituacao()) || SituacaoPedido.VENCIDO.equals(getSituacao())) {
            throw new RuntimeException("Não foi possível concluir o pedido pois ele se encontra na situação " + getSituacao().getEstado());
        }
    }

    private void validarPedidoParaCancelar() {
        if (SituacaoPedido.CANCELADO.equals(getSituacao()) || SituacaoPedido.VENCIDO.equals(getSituacao())) {
            throw new RuntimeException("Não foi possível cancelar o pedido pois ele se encontra na situação " + getSituacao().getEstado());
        }
    }

    private void removerProdutosDoPedidoDoEstoque() {
        getCarrinho().getItens().forEach(itemPedido -> {
            itemPedido.getProduto().getEstoque().diminuirQuantidade(itemPedido.getQuantidade());
        });
    }

    @Override
    public String toString() {
        this.calcularValorTotal();
        StringBuilder pedidoStr = new StringBuilder();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormat df = new DecimalFormat("0.00");

        double valorDescontoEmPorcentagem = (1 - (getValorComDesconto() / getValorTotal()));

        pedidoStr.append("=-=-=-=-=-=-=-=-=-=-= Informações do pedido =-=-=-=-=-=-=-=-=-=-=\n");
        pedidoStr.append("Código: ").append(getCodigo()).append("\n");
        pedidoStr.append("Situação: ").append(getSituacao().getEstado()).append("\n");
        pedidoStr.append("Forma de pagamento: ").append(getFormaPagamento().getDescricao()).append("\n");
        pedidoStr.append("Data de efetuação do pedido: ").append(dtf.format(getData())).append("\n");
        pedidoStr.append("Cliente\n");
        pedidoStr.append("\tNome: ").append(cliente.getNome()).append("\n");
        pedidoStr.append("\tCPF/CNPJ: ").append(cliente.getCNPJOuCPF()).append("\n");
        pedidoStr.append("Itens do pedido\n");

        for (Item item : getCarrinho().getItens()) {
            pedidoStr.append("\t").append(item.getProduto().getNome()).append("\n");
            pedidoStr.append("\t\t").append(item.getQuantidade()).append(" x  R$ ").append(df.format(item.getValorUnitario())).append(" =  R$ ").append(df.format(item.getValorItem())).append("\n");
        }

        pedidoStr.append("(+) Valor Total: R$ ").append(df.format(getValorTotal())).append("\n");
        pedidoStr.append("(-) Desconto:    R$ ").append(df.format(getDesconto())).append(" (").append(df.format(valorDescontoEmPorcentagem)).append("%)\n");
        pedidoStr.append("(=) Valor Final: R$ ").append(df.format(getValorComDesconto())).append("\n");
        
        pedidoStr.append("Informações da nota fiscal\n");
        pedidoStr.append(getNotaFiscal());

        return pedidoStr.toString();
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

    private void setSituacao(SituacaoPedido situacao) {
        this.situacao = situacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getValorComDesconto() {
        this.calcularValorTotal();
        return getValorTotal() - getDesconto();
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

}
