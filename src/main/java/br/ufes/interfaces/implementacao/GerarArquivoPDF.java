package br.ufes.interfaces.implementacao;

import br.ufes.interfaces.IGeradorArquivo;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class GerarArquivoPDF implements IGeradorArquivo {
    
    @Override
    public void gerarArquivo(Object src, String nomeArquivo) throws DocumentException {

        Document document = new Document();
        
        try {
            File file = new File("Saida/pdf/" + nomeArquivo + ".pdf");
            file.getParentFile().mkdirs();
            
            PdfWriter.getInstance(document, new FileOutputStream(file));
        } catch (FileNotFoundException ex) {
            System.out.println("Error:" + ex);
        }
        document.open();
        document.add(new Paragraph(src.toString()));
        document.close();

    }
    
}
