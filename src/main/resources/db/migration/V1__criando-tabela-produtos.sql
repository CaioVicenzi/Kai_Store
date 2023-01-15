create table produtos(
	id int primary key AUTO_INCREMENT,
	nome varchar(100) not null,
	descricao varchar(200) not null,
	preco float not null,
	categoria varchar(50) not null
);