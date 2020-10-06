package br.ufes.interfaces.implementacao;

import br.ufes.interfaces.IPoliticaDeDesconto;
import br.ufes.model.CarrinhoDeCompra;

public class DescontoPorPontuacao implements IPoliticaDeDesconto {

    @Override
    public double calcularDesconto(CarrinhoDeCompra carrinho) {
        double pontos = carrinho.getCliente().getPontuacao();
        if (pontos < 30) {
            return carrinho.getValor() * (0 * pontos);
        } else if (pontos < 50) {
            return carrinho.getValor() * (0.05 * pontos);
        } else if (pontos < 70) {
            return carrinho.getValor() * (0.10 * pontos);
        } else {
            return carrinho.getValor() * (0.15 * pontos);
        }
    }

}
