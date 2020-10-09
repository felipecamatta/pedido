package br.ufes.interfaces.implementacao;

import br.ufes.interfaces.IGeradorArquivo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

public class GerarArquivoJSON implements IGeradorArquivo {

    @Override
    public void gerarArquivo(Object src, String nomeArquivo) throws Exception {
        if(src == null) {
            throw new RuntimeException("O objeto fonte das informações não foi fornecido");
        }
        
        if(nomeArquivo == null || nomeArquivo.isBlank()) {
            throw new RuntimeException("Nome do arquivo não fornecido");
        }
        
        File file = new File("Saida/json/" + nomeArquivo + ".json");
        file.getParentFile().mkdirs();
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        Writer writer = new FileWriter(file);
        
        gson.toJson(src, writer);
        
        writer.close();
        
    }

}
