package br.ufes.interfaces.implementacao;

import br.ufes.interfaces.IPoliticaDeDesconto;
import br.ufes.model.CarrinhoDeCompra;
import br.ufes.model.Item;

public class DescontoPorTipoProduto implements IPoliticaDeDesconto {

    @Override
    public double calcularDesconto(CarrinhoDeCompra carrinho) {
        double valorDesconto = 0;
        for (Item itens : carrinho.getItens()) {
            valorDesconto += itens.getValorItem() * itens.getProduto().getTipoProduto().getPercDesconto();
        }
        return valorDesconto;
    }

}
