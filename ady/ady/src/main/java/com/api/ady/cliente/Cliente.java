package com.api.ady.cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Cliente")
@Entity(name = "Cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {
public Cliente(dadosCadastroCliente dados) {
	this.nome = dados.nome();
	this.telefone = dados.telefone();
	this.endereco = dados.endereco();
	this.servico = dados.servico();
	this.status = dados.status();
	this.data_pedido = dados.data_pedido();
	this.data_entrega = dados.data_entrega();
	this.valor = dados.valor();
}	


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String nome;
private String telefone;
private String endereco;
private String servico;
private String status;
private String data_pedido;
private String data_entrega;
private String valor;



public void atualizaInformacoes(dadosAlteracaoCliente dados) {
	if(dados.nome() != null) {
		this.nome = dados.nome();
	}
	if(dados.telefone() != null) {
		this.telefone = dados.telefone();
	}
	if(dados.endereco() != null) {
		this.endereco = dados.endereco();
	}
	if(dados.servico() != null) {
		this.servico = dados.servico();
	}
	if(dados.id() != null && dados.id() == 0) {
		this.id = dados.id();
	}
	if(dados.status() != null) {
		this.status = dados.status();
	}
	if(dados.data_pedido() != null) {
		this.data_pedido = dados.data_pedido();
	}
	if(dados.data_entrega() != null) {
		this.data_entrega = dados.data_entrega();
	}
	if(dados.valor() != null) {
		this.valor = dados.valor();
	}
	
	
}
public void atualizaStatus(String status) {
	this.status = status;
}
}
