package br.ufes.model;
public class TipoProduto {
    public double percDesconto = 0.0;
    public String tipo;

    public TipoProduto(double percDesconto, String tipo) {
        this.percDesconto = percDesconto;
        this.tipo = tipo;
    }

       
    public double getPercDesconto() {
        return percDesconto;
    }

    public void setPercDesconto(double percDesconto) {
        this.percDesconto = percDesconto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
