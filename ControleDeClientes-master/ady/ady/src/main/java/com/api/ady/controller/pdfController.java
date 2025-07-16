package com.api.ady.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.api.ady.cliente.Cliente;
import com.api.ady.pdfGenerator.ClientService;
import com.api.ady.pdfGenerator.PdfGeneratorService;

@RestController
public class pdfController {

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private PdfGeneratorService pdfGeneratorService;
	
	
	
	@GetMapping("/pdf/{id}")
	public ResponseEntity<byte[]> donwloadClientsPdf(@PathVariable Long id){
		Cliente cliente = clientService.getClienteById(id);
		if (cliente == null) {
	        return ResponseEntity.notFound().build();
	    }
		
		
		byte[] pdfContent = pdfGeneratorService.geradorPdfCliente(List.of(cliente));
		

		return ResponseEntity.ok()
	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=cliente_" + id + ".pdf")
	            .contentType(MediaType.APPLICATION_PDF)
	            .body(pdfContent);
	}
	
}
