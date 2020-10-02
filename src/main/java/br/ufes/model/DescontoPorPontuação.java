/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.model;

import java.time.Month;

/**
 *
 * @author gabriel
 */
public class DescontoPorPontuação implements IPoliticaDeDesconto{
    
    public final byte pontuacao = 30; // coloquei pontuação 30 apenas como exemplo

    @Override
    public double calcularDesconto(CarrinhoDeCompra carrinho) {
        if(carrinho.getCliente().getPontuacao() >= pontuacao){
            return carrinho.getValor()*0.05;
        }
        
        return 0;
    }
    
}
