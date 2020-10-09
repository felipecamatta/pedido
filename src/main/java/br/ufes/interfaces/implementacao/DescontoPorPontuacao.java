package br.ufes.interfaces.implementacao;

import br.ufes.interfaces.IPoliticaDeDesconto;
import br.ufes.model.Pedido;

public class DescontoPorPontuacao implements IPoliticaDeDesconto {

    @Override
    public double calcularDesconto(Pedido pedido) {
        double pontos = pedido.getCliente().getPontuacao();
        if (pontos < 30) {
            return pedido.getValorTotal() * (0 * pontos);
        } else if (pontos < 50) {
            return pedido.getValorTotal() * (0.05 * pontos);
        } else if (pontos < 70) {
            return pedido.getValorTotal() * (0.10 * pontos);
        } else {
            return pedido.getValorTotal() * (0.15 * pontos);
        }
    }

}
