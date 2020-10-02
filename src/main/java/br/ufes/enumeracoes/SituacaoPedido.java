/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.enumeracoes;

/**
 *
 * @author gabriel
 */
public enum SituacaoPedido {
    PAGO("PAGO"), PENDENTE("PENDENTE"), CANCELADO("CANCELADO"), VENCIDO("VENCIDO");
    
    private final String estado;
    
    SituacaoPedido(String estado){
        this.estado = estado;
    }
    
    public String getEstado(){
        return this.estado;
    }
}
