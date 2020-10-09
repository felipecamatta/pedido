package br.ufes;

import br.ufes.enumeracoes.FormaPagamento;
import br.ufes.enumeracoes.UF;
import br.ufes.interfaces.implementacao.GerarArquivoJSON;
import br.ufes.model.Cliente;
import br.ufes.model.CarrinhoDeCompra;
import br.ufes.interfaces.implementacao.GerarArquivoPDF;
import br.ufes.model.Endereco;
import br.ufes.model.Pedido;
import br.ufes.model.Produto;
import br.ufes.model.TipoProduto;
import br.ufes.util.ICMSUtil;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        try {

            ICMSUtil.lerArquivoICMS();
            
            Principal p = new Principal();
            p.menu();

        } catch (RuntimeException rte) {
            System.err.println("Falha: " + rte.getMessage());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void menu() {
        GerarArquivoPDF emitir_pdf = new GerarArquivoPDF();
        GerarArquivoJSON emitir_json = new GerarArquivoJSON();
        
        Cliente cliente1 = new Cliente("Fulano", "123.456.789-01"); //gera um cliente
        Endereco enderecoCliente = new Endereco("Rua Maria Deta Lond", "Casa", "30622-490", "Bonsucesso (Barreiro)", "Belo Horizonte", 247, UF.MG );
        cliente1.setEndereco(enderecoCliente);
        Endereco enderecoLoja = new Endereco("Rua Dr. Wanderley", "Prédio", "29500-000", "Centro", "Alegre", 20, UF.ES);

        List<Produto> produtos = new ArrayList<>(); //Lista de produtos
        /*
             *Pré-cadastra uma série de produtos 
         */
        produtos.add(new Produto("Caneta Bic", 3.50, 8, new TipoProduto(0.05, "caneta")));
        produtos.add(new Produto("Caderno HotWheels", 3.50, 8, new TipoProduto(0.05, "caderno")));
        produtos.add(new Produto("Faber Castel", 3.50, 8, new TipoProduto(0.05, "borracha")));
        produtos.add(new Produto("Tilibra", 3.50, 8, new TipoProduto(0.05, "lápis")));

        int op = -1, op1 = op, formaPagamento;
        FormaPagamento formaPagamentoPedido;
        while (op!=0) {
            formaPagamentoPedido = null;
            formaPagamento = -1;
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
                                while (formaPagamento == -1) {
                                    System.out.println("Escolha a forma de pagamento: ");
                                    System.out.println("1 - Boleto");
                                    System.out.println("2 - Cartão de Crédito");
                                    System.out.println("3 - Cartão de Débito");
                                    
                                    formaPagamento = sc.nextInt();
                                    
                                    switch (formaPagamento) {
                                        case 1:
                                            formaPagamentoPedido = FormaPagamento.BOLETO;
                                            break;
                                        case 2: 
                                            formaPagamentoPedido = FormaPagamento.CARTAO_CREDITO;
                                            break;
                                        case 3:
                                            formaPagamentoPedido = FormaPagamento.CARTAO_DEBITO;
                                            break;
                                        default:
                                            formaPagamento = -1;
                                            break;
                                    }
                                }
                                
                                Pedido pedido = carrinho.fechar(enderecoLoja, formaPagamentoPedido);
                                
                                pedido.concluir();
                                
                                emitir_pdf.gerarArquivo(pedido, "Nota Fiscal");
                                emitir_json.gerarArquivo(pedido, "Nota Fiscal");
                                
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
