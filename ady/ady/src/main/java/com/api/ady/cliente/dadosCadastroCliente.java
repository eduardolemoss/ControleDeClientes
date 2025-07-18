package com.api.ady.cliente;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public record dadosCadastroCliente(String nome,String telefone, String endereco, String servico, String status, 

@JsonFormat(pattern = "dd/MM/yyyy")  
LocalDate data_pedido, 
@JsonFormat(pattern = "dd/MM/yyyy")  
LocalDate data_entrega, 
Float valor) {

}
