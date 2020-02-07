CREATE TABLE frete (
	id INT NOT NULL AUTO_INCREMENT,
  	descricao VARCHAR(30) NOT NULL,
	peso float NOT NULL,
	valor float NOT NULL,
	cliente_id INTEGER,
	cidade_id INTEGER,
	PRIMARY KEY (id),
	FOREIGN KEY(cliente_id) REFERENCES cliente(id),
	FOREIGN KEY(cidade_id) REFERENCES cidade(id)	
);
