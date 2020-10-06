package br.ufes.interfaces;

import br.ufes.model.CarrinhoDeCompra;

public interface IPoliticaDeDesconto {

    public double calcularDesconto(CarrinhoDeCompra carrinho);

}
