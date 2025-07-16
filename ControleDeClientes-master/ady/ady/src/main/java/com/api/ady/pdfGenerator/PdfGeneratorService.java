package com.api.ady.pdfGenerator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


import org.springframework.stereotype.Service;

import com.api.ady.cliente.Cliente;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;

@Service
public class PdfGeneratorService {
public byte[] geradorPdfCliente(List<Cliente> clientes) {
	try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
		PdfWriter leitor = new PdfWriter(outputStream);
		PdfDocument pdf = new PdfDocument(leitor);
		Document document = new Document(pdf);
		
		 PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
	        PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

	        Paragraph title = new Paragraph("Nota AdyVidros")
	                .setFont(boldFont)
	                .setFontSize(18)
	                .setMarginBottom(20);
	        document.add(title);
	        
	        float[] columnWidths = {200F, 800F};  
            Table table = new Table(columnWidths);

            
            for (Cliente cliente : clientes) {
                
                addRow(table, "Nome:", cliente.getNome());
                addRow(table, "Telefone:", cliente.getTelefone());
                addRow(table, "Endereço:", cliente.getEndereco());
                addRow(table, "Serviço:", cliente.getServico());
                addRow(table, "Status:", cliente.getStatus());
                addRow(table, "Data do Pedido:", cliente.getData_pedido());
                addRow(table, "Data da Entrega:", cliente.getData_entrega());
                addRow(table, "Valor:", cliente.getValor());
            }

            document.add(table);
            document.close();

            return outputStream.toByteArray(); 
        } catch (Exception e) {
            e.printStackTrace();
            return null;
            
        }
    }

private void addRow(Table table, String label, String value) {
    try {
       
        PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
        PdfFont regularFont = PdfFontFactory.createFont(StandardFonts.HELVETICA);

        table.addCell(new Paragraph(label).setFont(boldFont));

        table.addCell(new Paragraph(value).setFont(regularFont));

    } catch (IOException e) {
        e.printStackTrace(); 
    }
}

}
