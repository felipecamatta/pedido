package br.ufes.business;

import br.ufes.enumeracoes.SituacaoPedido;
import br.ufes.model.Item;
import br.ufes.model.Pedido;
import java.time.LocalDate;

/**
 *
 * @author rborges
 */
public class PedidoBusiness {
    
    public void concluir(Pedido pedido) {
        if(LocalDate.now().isAfter(pedido.getDataValidade())) {
            pedido.setSituacao(SituacaoPedido.VENCIDO);
            throw new RuntimeException("Não foi possível concluir o pedido pois ele expirou");
        }
        
        removerProdutosDoPedidoDoEstoque(pedido);
    }
    
    private void removerProdutosDoPedidoDoEstoque(Pedido pedido) {
        for(Item itemPedido : pedido.getCarrinho().getItens()) {
            itemPedido.getProduto().getEstoque().diminuirQuantidade(itemPedido.getQuantidade());
        }
    }
    
}
