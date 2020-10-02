/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.interfaces;

import br.ufes.model.CarrinhoDeCompra;

/**
 *
 * @author gabriel
 */
public interface IPoliticaDeDesconto {
    
    public double calcularDesconto(CarrinhoDeCompra carrinho);
    
}
