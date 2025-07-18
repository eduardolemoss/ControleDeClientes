CREATE TABLE cliente(
	id int primary key auto_increment,
	nome varchar (50),
	telefone varchar(22),
	endereco varchar(500),
	servico varchar (500),
	status varchar(20),
	data_pedido date,
	data_entrega date,
	valor float(10,2)
);
CREATE TABLE usuario(
	id int not null auto_increment primary key,
	login varchar(50),
	senha varchar(2000)
);