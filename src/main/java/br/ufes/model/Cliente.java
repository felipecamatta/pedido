package br.ufes.model;

import java.time.LocalDate;

public final class Cliente {

    private final String nome;
    private final String CNPJOuCPF;
    private Endereco endereco;
    private LocalDate dataNascimento;
    private double pontuacao;

    public Cliente(String nome, String CNPJOuCPF, Endereco endereco, LocalDate dataNascimento) {
        this.nome = nome;
        this.CNPJOuCPF = CNPJOuCPF;
        this.endereco = endereco;
        this.dataNascimento = dataNascimento;
    }

    public Cliente(String nome, String codigo) {
        this.nome = nome;
        this.CNPJOuCPF = codigo;
    }

    public void incrementarPontuacao(double valorParaIncrementar) {
        if (valorParaIncrementar > 0) {
            pontuacao += valorParaIncrementar;
        }
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

    public double getPontuacao() {
        return pontuacao;
    }

    @Override
    public String toString() {
        return "Cliente: " + nome + ", CNPJ/CPF: " + CNPJOuCPF;
    }

}
