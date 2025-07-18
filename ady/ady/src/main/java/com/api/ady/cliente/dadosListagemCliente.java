package com.api.ady.cliente;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public record dadosListagemCliente(Long id,String nome,String telefone, String endereco, String servico, String status, @JsonFormat(pattern = "dd/MM/yyyy")  
LocalDate data_pedido, 

@JsonFormat(pattern = "dd/MM/yyyy")  
LocalDate data_entrega, Float valor) {
	public dadosListagemCliente(Cliente dados) {
		this(dados.getId(),dados.getNome(), dados.getTelefone(), dados.getEndereco(), dados.getServico(), dados.getStatus(), dados.getData_pedido(), dados.getData_entrega(), dados.getValor());
	}

}
