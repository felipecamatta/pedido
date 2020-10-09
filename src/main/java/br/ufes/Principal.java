package br.ufes;

import br.ufes.model.Cliente;
import br.ufes.model.CarrinhoDeCompra;
import br.ufes.interfaces.implementacao.GerarArquivoPDF;
import br.ufes.model.Produto;
import br.ufes.model.TipoProduto;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        try {

            /*CarrinhoDeCompra pedido1 = new CarrinhoDeCompra(cliente1,
                    produto1, 5,
                    LocalDate.now()
            );

            pedido1.addItem(new Produto("Folha Papel A4", 0.05, 10, new TipoProduto(0.1, "Folha")), 10);

            System.out.println(pedido1);*/
            Principal p = new Principal();
            p.menu();

        } catch (RuntimeException rte) {
            System.err.println("Falha: " + rte.getMessage());

        }

    }

    public void menu() {
        GerarArquivoPDF emitir_pdf = new GerarArquivoPDF();
        Cliente cliente1 = new Cliente("Fulano", "123.456.789-01"); //gera um cliente

        List<Produto> produtos = new ArrayList<>(); //Lista de produtos
        /*
             *Pré-cadastra uma série de produtos 
         */
        produtos.add(new Produto("Caneta Bic", 3.50, 8, new TipoProduto(0.05, "caneta")));
        produtos.add(new Produto("Caderno HotWheels", 3.50, 8, new TipoProduto(0.05, "caderno")));
        produtos.add(new Produto("Faber Castel", 3.50, 8, new TipoProduto(0.05, "borracha")));
        produtos.add(new Produto("Tilibra", 3.50, 8, new TipoProduto(0.05, "lápis")));

        int op = -1, op1 = op;
        while (op!=0) {
            System.out.println("Digite 1 para fazer um pedido\n"
                    + "Digite 0 para sair:\n");
            Scanner sc = new Scanner(System.in);
            op = sc.nextInt();//lê opcão
            switch (op) {
                //Opção zero encerra menu.
                case 0:
                    System.out.println("Encerrando...");
                    break;

                case 1:
                    CarrinhoDeCompra carrinho = null;
                    while (true) {
                        try {
                            System.out.print("Selecione o produto desejado na lista abaixo\n"
                            + "ou digite 0 pra fechar a compra\n"
                            + "digite -1 para cancelar e voltar ao menu principal:\n");
                            int cod = 1;
                            //imprime a lista de produtos
                            for (Produto p : produtos) {
                                System.out.println(cod + " - " + p.toString());
                                cod++;
                            }
                            op1 = sc.nextInt();//lê opcão
                            int qtd = 0; //quantidade de produtos desejado
                            if(op1 < 0){
                                break;//aqui volta ao menu anterior e cancela a compra
                            }
                            if (op1 > produtos.size()) {
                                System.out.println("Produto não encontrado!\n");
                            } else if (op1 == 0) {
                                emitir_pdf.gerarArquivo("Nota Fiscal", carrinho.toString());
                                break; //confirma a compra
                            } else {
                                System.out.println("Digite a quantidade desejada:");
                                qtd = sc.nextInt();
                                if (carrinho == null) {
                                    carrinho = new CarrinhoDeCompra(cliente1, produtos.get(op1-1), qtd, LocalDate.now());
                                } else {
                                    carrinho.addItem(produtos.get(op1-1), qtd);
                                }
                            }

                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    break; //encerra a opção 1

                default:
                    System.out.println("Opção invalida, tente novamente!");
                    break;
            }
        }
    }
}
