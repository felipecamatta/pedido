package br.ufes.interfaces;

import br.ufes.model.Pedido;

public interface IPoliticaDeDesconto {

    public double calcularDesconto(Pedido pedido);

}
