package br.ufes.model;

public final class Produto {

    private String nome;
    private double valorUnitario;
    private double valorUltimaCompra;
    private String tipo;
    private Estoque estoque;

    public Produto(String nome, double valorUnitario, double quantidade) {
        this.nome = nome;
        setValorUnitario(valorUnitario);
        this.estoque = new Estoque(quantidade);
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    @Override
    public String toString() {
        return "Produto: " + nome
                + ", valor unitario: R$" + valorUnitario
                + ", valor da ultima compra: R$" + valorUltimaCompra
                + ", quantidade em estoque: " + this.getEstoque().getQuantidade();
    }

}
