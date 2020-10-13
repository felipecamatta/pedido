package br.ufes.interfaces.implementacao;

import br.ufes.interfaces.IPoliticaDeDesconto;
import br.ufes.model.Item;
import br.ufes.model.Pedido;

public class DescontoPorTipoProduto implements IPoliticaDeDesconto {

    @Override
    public double calcularDesconto(Pedido pedido) {
        double valorDesconto = 0;
        for (Item itens : pedido.getCarrinho().getItens()) {//Aplicado em cima do valor total do item, sem considerar descontos prévios
            valorDesconto += itens.getValorItem() * itens.getProduto().getTipoProduto().getDesconto();
        }
        return valorDesconto;
    }

}
