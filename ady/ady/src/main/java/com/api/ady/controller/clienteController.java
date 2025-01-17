package com.api.ady.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.ady.cliente.Cliente;
import com.api.ady.cliente.ClienteRepository;
import com.api.ady.cliente.dadosAlteracaoCliente;
import com.api.ady.cliente.dadosCadastroCliente;
import com.api.ady.cliente.dadosListagemCliente;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/registros")
public class clienteController {

	@Autowired
	private ClienteRepository clienteRepository; 
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody dadosCadastroCliente dados){
		var cliente = new Cliente(dados);
		clienteRepository.save(cliente);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
.toUri();
		return ResponseEntity.created(location).body(cliente);
	}
	@GetMapping
	public ResponseEntity<List<dadosListagemCliente>> listar(@RequestParam(required = false)String search) {
		List<dadosListagemCliente> lista;
		if(search != null) {
			lista = clienteRepository.findBynomeContaining(search).stream().map(dadosListagemCliente:: new).toList();
		}
		else {
			lista = clienteRepository.findAll().stream().map(dadosListagemCliente::new).toList();
		}
		return ResponseEntity.ok(lista);
	}
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody dadosAlteracaoCliente dados){
		if(!clienteRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
		}
		var cliente = clienteRepository.getReferenceById(id);
		cliente.atualizaInformacoes(dados);
		return ResponseEntity.ok(dados);
	}
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> excluir(@PathVariable Long id){
		if(!clienteRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
		}
		clienteRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/status")
	public ResponseEntity<List<dadosListagemCliente>> listarStatus(){
		var lista = clienteRepository.findBystatusContaining("PENDENTE").stream().map(dadosListagemCliente:: new).toList();
		return ResponseEntity.ok(lista);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> detalhar(@PathVariable Long id){
		if(!clienteRepository.existsById(id)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
		}
		var cliente = clienteRepository.getReferenceById(id);
		dadosListagemCliente dados = new dadosListagemCliente(cliente);
		return ResponseEntity.ok(dados);
	}
	@PutMapping("/{id}/finalizar")
	public ResponseEntity<?> atualizarStatusCliente(@PathVariable Long id){ 
		Cliente cliente = clienteRepository.findById(id).orElse(null);
		if(cliente == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
		}
		cliente.setStatus("FINALIZADO");
	    
	   
		clienteRepository.save(cliente);
		return ResponseEntity.ok("Status atualizado");
	}
	
	
	
	
}
