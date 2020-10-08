package br.ufes.interfaces.implementacao;

//import com.itextpdf.text.Document;
import br.ufes.interfaces.IGeradorArquivo;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;

public class GerarArquivoPDF implements IGeradorArquivo{

    public GerarArquivoPDF() {
    }
    
    

    public void gerarArquivo(String titulo, String saida) throws DocumentException {

        Document document = new Document();
        
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Saida/pdf/"+titulo+".pdf"));
        } catch (FileNotFoundException ex) {
            System.out.println("Error:" + ex);
        }
        document.open();
        document.add(new Paragraph(saida));
        document.close();

    }
    
}
