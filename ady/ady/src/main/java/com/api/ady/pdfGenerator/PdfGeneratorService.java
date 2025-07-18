package com.api.ady.pdfGenerator;

import java.io.ByteArrayOutputStream; 
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.api.ady.cliente.Cliente;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;

@Service
public class PdfGeneratorService {
public byte[] geradorPdfCliente(List<Cliente> clientes) {
	try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
		PdfWriter leitor = new PdfWriter(outputStream);
		PdfDocument pdf = new PdfDocument(leitor);
		Document document = new Document(pdf);
		
		 PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
	        PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

	        URL logoUrl = getClass().getClassLoader().getResource("static/logo.png");

	        if (logoUrl != null) {
	            ImageData data = ImageDataFactory.create(logoUrl);
	            Image logo = new Image(data);
	            logo.setWidth(100);
	            document.add(logo);
	        } else {
	            System.out.println("Logo não encontrado no classpath.");
	        }

            // 2. Cabeçalho fixo da loja
            Paragraph header = new Paragraph()
                    .add(new Text("AdyVidros\n").setFont(boldFont).setFontSize(14))
                    .add(new Text("CNPJ: 29.359.091/0001-38\n").setFont(font))
                    .add(new Text("Endereço: Rua Guarani, 261 – Piabetá – Magé\n").setFont(font))
                    .add(new Text("Telefone: (21) 97019-4988\n").setFont(font))
                    .setMarginBottom(10);
            document.add(header);

            // 3. Título
            Paragraph title = new Paragraph("NOTA DE SERVIÇO")
                    .setFont(boldFont)
                    .setFontSize(16)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(20);
            document.add(title);

            // 4. Tabela com os dados do cliente
            float[] columnWidths = {200F, 800F};
            Table table = new Table(columnWidths);

            for (Cliente cliente : clientes) {
                addRow(table, "Nome:", cliente.getNome());
                addRow(table, "Telefone:", cliente.getTelefone());
                addRow(table, "Endereço:", cliente.getEndereco());
                addRow(table, "Serviço:", cliente.getServico());
                addRow(table, "Status:", cliente.getStatus());
                addRow(table, "Data do Pedido:", formatDate(cliente.getData_pedido()));
                addRow(table, "Data da Entrega:", formatDate(cliente.getData_entrega()));
                addRow(table, "Valor:", String.format("%.2f", cliente.getValor()));
            }

            document.add(table);

            // 5. Espaço para assinatura
            Paragraph espacamento = new Paragraph("\n\n\n");
            document.add(espacamento);

            Paragraph assinatura = new Paragraph("____________________________________\nAssinatura do Cliente")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(12)
                    .setMarginTop(30);
            document.add(assinatura);

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

            table.addCell(new Paragraph(label).setFont(boldFont).setPaddingRight(10));
            try {
                double numericValue = Double.parseDouble(value);
                table.addCell(new Paragraph(String.format("%.2f", numericValue)).setFont(regularFont));
            } catch (NumberFormatException e) {
                table.addCell(new Paragraph(value).setFont(regularFont));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formatDate(LocalDate date) {
        if (date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return date.format(formatter);
        }
        return "";
    }
}
