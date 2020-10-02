/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.interfaces;

import br.ufes.model.CarrinhoDeCompra;
import java.time.Month;

/**
 *
 * @author gabriel
 */
public class DescontoPorClienteAniversariante implements IPoliticaDeDesconto{

    @Override
    public double calcularDesconto(CarrinhoDeCompra carrinho) {
        
        try{
            if(carrinho.getCliente().getDataNascimento().withYear(carrinho.getData().getYear()).getDayOfYear() == carrinho.getData().getDayOfYear()){
                return carrinho.getValor()*0.05;
            }
        }catch(Exception e){
            throw e;
        }
        
        return 0;
    }
    
}
