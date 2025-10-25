-- Inserção de novos produtos (se a tabela estiver vazia)
INSERT INTO PRODUTO (nome, descricao, preco, tamanho, disponivel, destaque)
VALUES ('Bacon Bites', 'Cubos de bacon crocantes.', 25.00, 'P', TRUE, TRUE);

INSERT INTO PRODUTO (nome, descricao, preco, tamanho, disponivel, destaque)
VALUES ('Torresmo de Costela', 'Sabor intenso e suculento.', 55.00, 'G', TRUE, FALSE);

-- Atualização de produtos existentes (para ativar o destaque)
UPDATE PRODUTO SET destaque = TRUE WHERE id_produto = 1;