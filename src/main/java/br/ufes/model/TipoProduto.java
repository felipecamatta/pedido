package br.ufes.model;

public class TipoProduto {

    public String tipo;
    public double desconto = 0.0;

    public TipoProduto(String tipo, double desconto) {
        this.tipo = tipo;
        this.setDesconto(desconto);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        if (desconto >= 1) {
            throw new RuntimeException("Informe um valor de desconto válido");
        }
        this.desconto = desconto;
    }

}
