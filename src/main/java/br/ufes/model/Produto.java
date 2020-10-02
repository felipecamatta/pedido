package br.ufes.model;

public final class Produto {

    private String nome;
    private double valorUnitario;
    private double valorUltimaCompra;
    private TipoProduto tipo;
    private Estoque estoque;

    public Produto(String nome, double valorUnitario, double quantidade, TipoProduto tipo) {
        this.nome = nome;
        setValorUnitario(valorUnitario);
        this.estoque = new Estoque(quantidade);
        this.tipo = tipo;
    }

    public boolean estoqueDisponivel(double quantidadeNecessaria) {
        return this.getEstoque().getQuantidade() >= quantidadeNecessaria;
    }

    public String getNome() {
        return nome;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public double getValorUltimaCompra() {
        return valorUltimaCompra;
    }

    public double getQuantidade() {
        return this.getEstoque().getQuantidade();
    }

    public void setNome(String nome) {
        if (nome == null) {
            throw new RuntimeException("Nome inválido: " + nome);
        }
        this.nome = nome;
    }

    public void setValorUnitario(double valorUnitario) {
        if (valorUnitario <= 0) {
            throw new RuntimeException("Valor inválido: " + valorUnitario);
        }
        this.valorUltimaCompra = this.valorUnitario;
        this.valorUnitario = valorUnitario;
    }

    public TipoProduto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProduto tipo) {
        this.tipo = tipo;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    @Override
    public String toString() {
        return "Produto: " + nome
                + ", tipo: " + getTipo().getTipo()
                + ", valor unitario: R$" + valorUnitario
                + ", valor da ultima compra: R$" + valorUltimaCompra
                + ", quantidade em estoque: " + this.getEstoque().getQuantidade();
    }

}
