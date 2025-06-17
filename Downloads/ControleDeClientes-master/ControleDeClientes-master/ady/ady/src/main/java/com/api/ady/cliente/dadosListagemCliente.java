package com.api.ady.cliente;

public record dadosListagemCliente(Long id,String nome,String telefone, String endereco, String servico, String status, String data_pedido, String data_entrega, String valor) {
	public dadosListagemCliente(Cliente dados) {
		this(dados.getId(),dados.getNome(), dados.getTelefone(), dados.getEndereco(), dados.getServico(), dados.getStatus(), dados.getData_pedido(), dados.getData_entrega(), dados.getValor());
	}

}
