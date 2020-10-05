package br.ufes.business;

import br.ufes.model.CarrinhoDeCompra;
import br.ufes.model.Pedido;
import java.time.LocalDate;

/**
 *
 * @author rborges
 */
public class CarrinhoDeCompraBusiness {
    
    public Pedido fechar(CarrinhoDeCompra carrinhoDeCompra) {
        // TODO: Mudar o número aleatório do código do pedido para o código assim que a regra de código do pedido for fechada
        Pedido pedido = new Pedido(Math.round(Math.random()), LocalDate.now(), carrinhoDeCompra.getValor(), LocalDate.now().plusDays(5), carrinhoDeCompra);
        
        return pedido;
    }
    
}
