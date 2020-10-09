package br.ufes.model;

public class Estoque {

    private double quantidade;

    public Estoque(double quantidade) {
        this.quantidade = quantidade;
    }

    public double aumentarQuantidade(double quantidade) {
        if (quantidade > 0) {
            setQuantidade(getQuantidade() + quantidade);
        }
        return getQuantidade();
    }

    public double diminuirQuantidade(double quantidade) {
        // TODO: Verificar o que fazer quando a quantidade para diminuir for maior que a disponível
        if (quantidade > 0) {
            double quantidadeAtual = getQuantidade();
            if (quantidade > quantidadeAtual) {
                setQuantidade(0);
            } else {
                setQuantidade(quantidadeAtual - quantidade);
            }
        }

        return getQuantidade();
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

}
