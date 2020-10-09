package br.ufes.util;

import br.ufes.model.Endereco;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class ICMSUtil {

    private static int tabela[][];

    public static void lerArquivoICMS() throws Exception {
        int tabelaAux[][] = new int[27][27];
        int i = 0;
        try {
            FileReader arq = new FileReader("ICMS.txt");
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();
            while (linha != null) {
                String[] valores = linha.split(";");
                if (valores.length != 27) {
                    throw new RuntimeException("Arquivo inválido");
                }
                for (int j = 0; j < 27; j++) {
                    int valor = Integer.parseInt(valores[j]);
                    tabelaAux[i][j] = valor;
                }
                linha = lerArq.readLine();
                i++;
            }
            if (i != 27) {
                throw new RuntimeException("Arquivo inválido");
            }
            tabela = tabelaAux;
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public static int consultarTaxa(Endereco enderecoOrigem, Endereco enderecoDestino) throws Exception {
        return tabela[enderecoOrigem.getUf().getCodigo()][enderecoDestino.getUf().getCodigo()];
    }
}
