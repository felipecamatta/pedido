package br.ufes.model;
import java.time.Month;

public class DescontoPorTipoProduto implements IPoliticaDeDesconto{
    public double valorDesconto = 0;
    @Override
    public double calcularDesconto(CarrinhoDeCompra carrinho) {
      for(Item  itens : carrinho.getItens()){
          valorDesconto += itens.getValorItem() * itens.getProduto().getTipo().getPercDesconto();
      }
        return carrinho.getValor() - valorDesconto;
    }
    
}
