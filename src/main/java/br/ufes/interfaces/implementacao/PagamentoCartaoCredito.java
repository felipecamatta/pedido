package br.ufes.interfaces.implementacao;

import br.ufes.interfaces.IFormaPagamento;
import br.ufes.model.Pedido;

public class PagamentoCartaoCredito implements IFormaPagamento {

    @Override
    public void realizarPagamento(Pedido pedido) {
    }

}
