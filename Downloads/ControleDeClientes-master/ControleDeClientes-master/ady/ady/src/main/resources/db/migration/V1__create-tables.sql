CREATE TABLE cliente(
	id int primary key auto_increment,
	nome varchar (50),
	telefone varchar(22),
	endereco varchar(500),
	servico varchar (500),
	status varchar(20),
	data_pedido varchar(10),
	data_entrega varchar(10),
	valor float(10, 2)
);