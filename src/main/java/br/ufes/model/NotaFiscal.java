/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.model;

/**
 *
 * @author gabriel
 */
public class NotaFiscal {
    
    private long codigo;
    private double taxaICMS;
    private double valorICMS;

    public NotaFiscal(long codigo, double taxaICMS, double valorICMS) {
        this.codigo = codigo;
        this.taxaICMS = taxaICMS;
        this.valorICMS = valorICMS;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
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
