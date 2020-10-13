package br.ufes.interfaces.implementacao;

import br.ufes.interfaces.IPoliticaDeDesconto;
import br.ufes.model.Pedido;

public class DescontoPorPontuacao implements IPoliticaDeDesconto {

    @Override
    public double calcularDesconto(Pedido pedido) {
        double pontos = pedido.getCliente().getPontuacao();
        if (pontos < 30) {
            return 0;
        } else if (pontos < 50) {
            pedido.getCliente().diminuirPontuacao(10);
            return (pedido.getValorTotal() - pedido.getDesconto()) * 0.05;
        } else if (pontos < 70) {
            pedido.getCliente().diminuirPontuacao(20);
            return (pedido.getValorTotal() - pedido.getDesconto()) * 0.1;
        } else {
            pedido.getCliente().diminuirPontuacao(30);
            return (pedido.getValorTotal() - pedido.getDesconto()) * 0.15;
        }
    }

}
