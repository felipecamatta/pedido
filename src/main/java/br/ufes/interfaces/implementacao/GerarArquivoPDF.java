package br.ufes.interfaces.implementacao;

//import com.itextpdf.text.Document;
import br.ufes.interfaces.IGeradorArquivo;

//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfWriter;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
public class GerarArquivoPDF implements IGeradorArquivo {
    // TODO: Realizar gerador de pdf
    /*public void gerarArquivo(Pedido pedido) {

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("NOVOPDF.pdf"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GerarArquivoPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        document.open();
        document.add(new Paragraph("ADICIONAR OS DADOS DE PEDIDO AQUI PARA O PDF"));
        finally{
            document.close();
        }

        try {
            Desktop.getDesktop().open(new File("NOVOPDF.pdf"));
        } catch (IOException ex) {
            System.out.println("Error:" + ex);
        }

    }*/
}
