package br.ufes.model;

public class TipoProduto {

    public double desconto = 0.0;
    public String tipo;

    public TipoProduto(double percenutalDesconto, String tipo) {
        this.setDesconto(desconto);
        this.tipo = tipo;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double percDesconto) {
        if (percDesconto >= 1) {
            throw new RuntimeException("Informe um valor de desconto válido");
        }
        this.desconto = percDesconto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
