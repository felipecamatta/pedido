package br.ufes.model;

public final class Cliente {

    private final String nome;
    private final String CNPJOuCPF;
    private Endereco endereco;

    public Cliente(String nome, String CNPJOuCPF, Endereco endereco) {
        this.nome = nome;
        this.CNPJOuCPF = CNPJOuCPF;
        this.endereco = endereco;
    }
    
    public Cliente(String nome, String codigo) {
        this.nome = nome;
        this.CNPJOuCPF = codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getCNPJOuCPF() {
        return CNPJOuCPF;
    }

    @Override
    public String toString() {
        return "Cliente: " + nome + ", CNPJ/CPF = " + CNPJOuCPF;
    }

}
