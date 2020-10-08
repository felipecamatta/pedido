package br.ufes.interfaces.implementacao;

import br.ufes.interfaces.IFormaPagamento;
import br.ufes.model.Boleto;
import br.ufes.model.Pedido;

public class PagamentoBoleto implements IFormaPagamento {
    
    Boleto boleto = null;

    @Override
    public void realizarPagamento(Pedido pedido) {
        boleto = new Boleto(pedido.getValorTotal());
    }

    public Boleto getBoleto() {
        if(this.boleto == null)
            throw new RuntimeException("Pagamento não realizado!\n");
        return boleto;
    }
    
    
}
