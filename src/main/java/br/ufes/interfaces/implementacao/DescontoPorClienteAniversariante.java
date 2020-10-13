package br.ufes.interfaces.implementacao;

import br.ufes.interfaces.IPoliticaDeDesconto;
import br.ufes.model.Pedido;

public class DescontoPorClienteAniversariante implements IPoliticaDeDesconto {

    @Override
    public double calcularDesconto(Pedido pedido) {
        try {
            if (pedido.getCarrinho().getCliente().getDataNascimento().withYear(pedido.getData().getYear()).getDayOfYear() == pedido.getData().getDayOfYear()) {
                return (pedido.getValorTotal() - pedido.getDesconto()) * 0.05;
            } else {
                return 0;
            }
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível aplicar o desconto.");
        }
    }

}
