package br.ufes.interfaces.implementacao;

import br.ufes.interfaces.IPoliticaDeDesconto;
import br.ufes.model.CarrinhoDeCompra;

public class DescontoPorClienteAniversariante implements IPoliticaDeDesconto {

    @Override
    public double calcularDesconto(CarrinhoDeCompra carrinho) {

        try {
            if (carrinho.getCliente().getDataNascimento().withYear(carrinho.getData().getYear()).getDayOfYear() == carrinho.getData().getDayOfYear()) {
                return carrinho.getValor() * 0.05;
            }
        } catch (Exception e) {
            throw e;
        }

        return 0;
    }

}
