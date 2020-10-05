package br.ufes.model;

import java.time.LocalDate;

public final class Cliente {

    private final String nome;
    private final String CNPJOuCPF;
    private Endereco endereco;
    private LocalDate dataNascimento;
    private double pontuacao;

    public Cliente(String nome, String CNPJOuCPF, Endereco endereco, LocalDate dataNascimento, byte pontuacao) {
        this.nome = nome;
        this.CNPJOuCPF = CNPJOuCPF;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
        this.pontuacao = pontuacao;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public double getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(double pontuacao) {
        this.pontuacao = pontuacao;
    }
    
    @Override
    public String toString() {
        return "Cliente: " + nome + ", CNPJ/CPF = " + CNPJOuCPF;
    }

}
