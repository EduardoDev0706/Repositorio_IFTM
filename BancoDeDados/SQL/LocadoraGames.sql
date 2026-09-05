-- 1. CRIAÇÃO DO BANCO DE DADOS
CREATE DATABASE IF NOT EXISTS LocadoraGames;
USE LocadoraGames;

-- 2. CRIAÇÃO DAS TABELAS INDEPENDENTES (PAI)

-- TABELA CATEGORIA
CREATE TABLE Categoria (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nome_categoria VARCHAR(50) NOT NULL
);

-- TABELA VIDEOGAME
CREATE TABLE Videogame (
	id_videogame INT AUTO_INCREMENT PRIMARY KEY,
    nome_videogame VARCHAR(50) NOT NULL,
    ano_lancamento INT
);
-- TABELA CLIENTE
CREATE TABLE Cliente (
	id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nome_cliente VARCHAR(100) NOT NULL, 
    cpf VARCHAR(14) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    endereco VARCHAR(150),
    data_cadastro DATE
);
-- TABELA FUNCIONARIO
CREATE TABLE Funcionario (
	id_funcionario INT AUTO_INCREMENT PRIMARY KEY,
    nome_funcionario VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    endereco VARCHAR(150),
    valor_salario DECIMAL(10, 2)
);

-- 3. CRIAÇÃO DAS TABELAS DEPENDENTES (FILHO)
-- TABELA JOGO
-- Dependente de: Categoria e Videogame
CREATE TABLE Jogo (
	id_jogo INT AUTO_INCREMENT PRIMARY KEY,
    nome_jogo VARCHAR (100) NOT NULL, 
    ano_lancamento INT,
    valor_aluguel DECIMAL (10, 2) NOT NULL,
    quantidade_unidades INT DEFAULT 1,
    id_categoria INT,
    id_videogame INT,
    -- Adicao de Restricoes
    CONSTRAINT fk_jogo_categoria FOREIGN KEY (id_categoria) REFERENCES Categoria(id_categoria),
    CONSTRAINT fk_jogo_videogame FOREIGN KEY (id_videogame) REFERENCES Videogame(id_videogame)
);

-- TABELA LOCACAO
-- Depende de: Cliente, Funcionario e Jogo
CREATE TABLE Locacao (
    id_locacao INT AUTO_INCREMENT PRIMARY KEY,
    data_locacao DATE NOT NULL,
    valor_pago DECIMAL(10 , 2 ),
    data_prevista_devolucao DATE,
    data_real_devolucao DATE,
    id_cliente INT NOT NULL,
    id_funcionario INT NOT NULL,
    id_jogo INT NOT NULL,
    CONSTRAINT fk_locacao_cliente FOREIGN KEY (id_cliente)
        REFERENCES Cliente (id_cliente),
    CONSTRAINT fk_locacao_funcionario FOREIGN KEY (id_funcionario)
        REFERENCES Funcionario (id_funcionario),
    CONSTRAINT fk_locacao_jogo FOREIGN KEY (id_jogo)
        REFERENCES Jogo (id_jogo)
);

-- 4. INSERTS (3 REGISTROS POR TABELA)

-- Inserts Categoria
INSERT INTO Categoria (nome_categoria) VALUES ('Ação');
INSERT INTO Categoria (nome_categoria) VALUES ('RPG');
INSERT INTO Categoria (nome_categoria) VALUES ('Esportes');

-- Inserts Videogame
INSERT INTO Videogame (nome_videogame, ano_lancamento) VALUES ('Playstation 5', 2020);
INSERT INTO Videogame (nome_videogame, ano_lancamento) VALUES ('Xbox Series X', 2020);
INSERT INTO Videogame (nome_videogame, ano_lancamento) VALUES ('Nintendo Switch', 2017);

-- Inserts Cliente
INSERT INTO Cliente (nome_cliente, cpf, telefone, endereco, data_cadastro) 
VALUES ('João da Silva', '111.111.111-11', '(11) 99999-1111', 'Rua A, 100', '2023-01-10');
INSERT INTO Cliente (nome_cliente, cpf, telefone, endereco, data_cadastro) 
VALUES ('Maria Oliveira', '222.222.222-22', '(11) 99999-2222', 'Rua B, 200', '2023-02-15');
INSERT INTO Cliente (nome_cliente, cpf, telefone, endereco, data_cadastro) 
VALUES ('Carlos Souza', '333.333.333-33', '(11) 99999-3333', 'Rua C, 300', '2023-03-20');

-- Inserts Funcionario
INSERT INTO Funcionario (nome_funcionario, cpf, telefone, endereco, valor_salario) 
VALUES ('Ana Pereira', '444.444.444-44', '(11) 98888-4444', 'Av Central, 500', 2500.00);
INSERT INTO Funcionario (nome_funcionario, cpf, telefone, endereco, valor_salario) 
VALUES ('Roberto Santos', '555.555.555-55', '(11) 98888-5555', 'Av Norte, 600', 2500.00);
INSERT INTO Funcionario (nome_funcionario, cpf, telefone, endereco, valor_salario) 
VALUES ('Fernanda Lima', '666.666.666-66', '(11) 98888-6666', 'Av Sul, 700', 3200.00);

-- Inserts Jogos
-- God of War (Ação, PS5)
INSERT INTO Jogo (nome_jogo, ano_lancamento, valor_aluguel, quantidade_unidades, id_categoria, id_videogame)
VALUES ('God of War Ragnarok', 2022, 25.00, 5, 1, 1);

-- FIFA 24 (Esportes, Xbox)
INSERT INTO Jogo (nome_jogo, ano_lancamento, valor_aluguel, quantidade_unidades, id_categoria, id_videogame) 
VALUES ('EA FC 24', 2023, 20.00, 10, 3, 2);

-- Zelda (RPG, Switch)
INSERT INTO Jogo (nome_jogo, ano_lancamento, valor_aluguel, quantidade_unidades, id_categoria, id_videogame) 
VALUES ('Zelda: Breath of the Wild', 2017, 22.00, 3, 2, 3);

-- Inserts Locacao
-- Cliente 1 alugou Jogo 1 com Funcionario 1
INSERT INTO Locacao (data_locacao, valor_pago, data_prevista_devolucao, data_real_devolucao, id_cliente, id_funcionario, id_jogo) 
VALUES ('2023-10-01', 25.00, '2023-10-05', '2023-10-05', 1, 1, 1);

-- Cliente 2 alugou Jogo 2 com Funcionario 2
INSERT INTO Locacao (data_locacao, valor_pago, data_prevista_devolucao, data_real_devolucao, id_cliente, id_funcionario, id_jogo) 
VALUES ('2023-10-02', 20.00, '2023-10-06', NULL, 2, 2, 2);

-- Cliente 3 alugou Jogo 3 com Funcionario 1
INSERT INTO Locacao (data_locacao, valor_pago, data_prevista_devolucao, data_real_devolucao, id_cliente, id_funcionario, id_jogo) 
VALUES ('2023-10-03', 22.00, '2023-10-07', '2023-10-06', 3, 1, 3);
