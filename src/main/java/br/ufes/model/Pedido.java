/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.model;

import br.ufes.interfaces.IPoliticaDeDesconto;
import java.time.LocalDate;

/**
 *
 * @author gabriel
 */
public class Pedido {
    
    private long codigo;
    private LocalDate data;
    private double desconto;
    private double valorTotal;
    private LocalDate dataValidade;
    private CarrinhoDeCompra carrinho;
    private NotaFiscal notaFiscal;
    private IPoliticaDeDesconto politicaDeDesconto;

    public Pedido(long codigo, LocalDate data, double valorTotal, LocalDate dataValidade, CarrinhoDeCompra carrinho) {
        this.codigo = codigo;
        this.data = data;
        this.valorTotal = valorTotal;
        this.dataValidade = dataValidade;
        this.carrinho = carrinho;
    }
    
    
    public void removerItem(Item item){
        carrinho.removerItem(item);
    }
    
    public void alterarQuantidade(Item item, int quantidade){
        if(quantidade == 0){
            this.removerItem(item);
        }else if(quantidade > 0){
            this.carrinho.quantidadeProduto(item, quantidade);
        }
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public CarrinhoDeCompra getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(CarrinhoDeCompra carrinho) {
        this.carrinho = carrinho;
    }

    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
    }
   
    
}
