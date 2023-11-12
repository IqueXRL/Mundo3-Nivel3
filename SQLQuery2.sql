BEGIN TRANSACTION

	CREATE TABLE Pessoa(
		idPessoa INT PRIMARY KEY NOT NULL IDENTITY(1,1),
		nome VARCHAR(255),
		logradouro VARCHAR(255),
		cidade VARCHAR(255),
		estado CHAR(2),
		telefone VARCHAR(255),
		email VARCHAR(255),
	)

	CREATE TABLE Pessoa_juridica(
		idPessoa INT FOREIGN KEY REFERENCES Pessoa(idPessoa),
		cnpj varchar(18) PRIMARY KEY
	)

	CREATE TABLE Pessoa_fisica(
		idPessoa INT FOREIGN KEY REFERENCES Pessoa(idPessoa),
		cpf varchar(19) PRIMARY KEY
	)

	CREATE TABLE Usuarios(
		idUsuario INT PRIMARY KEY NOT NULL IDENTITY(1,1),
		login VARCHAR(255),
		senha VARCHAR(255)
	)

	CREATE TABLE Produtos(
		idProduto INT PRIMARY KEY NOT NULL IDENTITY(1,1),
		nome VARCHAR(255),
		quantidade INT,
		precoVenda DECIMAL(20,2)
	)

	CREATE TABLE Movimentos(
		idMovimento INT PRIMARY KEY NOT NULL IDENTITY(1,1),
		idUsuario INT FOREIGN KEY REFERENCES Usuarios(idUsuario),
		idPessoa INT FOREIGN KEY REFERENCES Pessoa(idPessoa),
		idProduto INT FOREIGN KEY REFERENCES Produtos(idProduto),
		quantidade INT,
		tipo CHAR(1),
		valorUnitario DECIMAL(20,2)
	)
IF @@ERROR=0
COMMIT
ELSE
ROLLBACK

BEGIN TRANSACTION
INSERT INTO Pessoa(nome, logradouro, cidade, estado, telefone, email)
VALUES
('Caique', 'Ruaua', 'Valinhos', 'SP', '19994017111', 'caique@email.com')
INSERT INTO Pessoa_fisica(idPessoa, cpf)
VALUES
((SELECT TOP(1) SCOPE_IDENTITY() FROM Pessoa), '222444666')


INSERT INTO Pessoa(nome, logradouro, cidade, estado, telefone, email)
VALUES
('Bait', 'Rua azul', 'Sousas', 'SP', '333555777', 'bait@email.com')
INSERT INTO Pessoa_juridica(idPessoa, cnpj)
VALUES
((SELECT TOP(1) SCOPE_IDENTITY() FROM Pessoa), '000222999')


IF @@ERROR=0
COMMIT
ELSE
ROLLBACK


select *  from Pessoa
select * FROM PESSOA AS P 
INNER JOIN Pessoa_juridica F ON P.idPessoa = F.idPessoa
where P.idPessoa = 12;   

DELETE FROM Pessoa_fisica
DELETE FROM Pessoa_juridica
DELETE FROM Pessoa

DROP TABLE Movimentos
DROP TABLE Usuarios
DROP TABLE Produtos
DROP TABLE Pessoa_fisica
DROP TABLE Pessoa_juridica
DROP TABLE Pessoa