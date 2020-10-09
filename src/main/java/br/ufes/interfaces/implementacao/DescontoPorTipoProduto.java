package br.ufes.interfaces.implementacao;

import br.ufes.interfaces.IPoliticaDeDesconto;
import br.ufes.model.Item;
import br.ufes.model.Pedido;

public class DescontoPorTipoProduto implements IPoliticaDeDesconto {

    @Override
    public double calcularDesconto(Pedido pedido) {
        double valorDesconto = 0;

        for (Item itens : pedido.getCarrinho().getItens()) {
            valorDesconto += itens.getValorItem() * itens.getProduto().getTipo().getDesconto();
        }

        return valorDesconto;
    }

}
