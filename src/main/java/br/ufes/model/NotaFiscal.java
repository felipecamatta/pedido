package br.ufes.model;

import java.text.DecimalFormat;
import java.util.UUID;

public class NotaFiscal {

    private final UUID codigo;
    private double taxaICMS;
    private double valorICMS;

    public NotaFiscal(double taxaICMS, double valorICMS) {
        this.codigo = UUID.randomUUID();
        this.taxaICMS = taxaICMS;
        this.valorICMS = valorICMS;
    }
    
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return "\tTaxa ICMS: " + df.format(taxaICMS) + "\n\tValor ICMS: " + df.format(valorICMS) + "\n";
    }

    public UUID getCodigo() {
        return codigo;
    }

    public double getTaxaICMS() {
        return taxaICMS;
    }

    public void setTaxaICMS(double taxaICMS) {
        this.taxaICMS = taxaICMS;
    }

    public double getValorICMS() {
        return valorICMS;
    }

    public void setValorICMS(double valorICMS) {
        this.valorICMS = valorICMS;
    }

}
