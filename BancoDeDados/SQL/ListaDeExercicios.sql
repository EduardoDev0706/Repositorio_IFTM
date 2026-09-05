-- SEÇÃO 1: Consultas Básicas

-- 1.1) Liste os pedidos que foram realizados durante o ano de 1996.
SELECT * FROM pedidos
WHERE dataPedido BETWEEN '1996-01-01' AND '1996-12-31';

-- 1.2) Liste os produtos que estão nas categorias 1 e 2.
SELECT * FROM Produtos 
WHERE categoriaID IN (1, 2);

-- 1.3) Liste o nome dos clientes que não moram em São Paulo.
SELECT nome 
FROM clientes 
WHERE cidade <> 'São Paulo';

-- 1.4) Liste os detalhes de pedidos que tiveram desconto.
SELECT * FROM detalhes_pedido 
WHERE desconto > 0;

-- 1.5) Liste os produtos que poderiam ser vendidos em uma loja.
SELECT * FROM produtos 
WHERE UnidadesEmEstoque > 0;

-- SEÇÃO 2: Filtros de Texto (LIKE)

-- 2.1) Liste as informações dos clientes que têm o nome começando com 'Maria'.
SELECT * FROM clientes 
WHERE nome LIKE 'Maria%';

-- 2.2) Liste os dados das categorias que possuem 'carne' como parte da descrição.
SELECT * FROM categorias 
WHERE descricao LIKE '%carne%';

-- 2.3) Liste os produtos que possuem nomes que começam com a letra C ou L.
SELECT * FROM produtos 
WHERE produtoNome LIKE 'C%' OR produtoNome LIKE 'L%';

-- 2.4) Liste as informações dos produtos que possuem nomes que não começam com C nem com L.
SELECT * FROM produtos 
WHERE produtoNome NOT LIKE 'C%' AND produtoNome NOT LIKE 'L%';

-- SEÇÃO 3: Filtros com Listas (IN / NOT IN)

-- 3.1) Listar os clientes que moram nos países: Brazil, Germany e Mexico.
SELECT * FROM clientes 
WHERE pais IN ('Brazil', 'Germany', 'Mexico');

-- 3.2) Listar os pedidos dos clientes QUICK, MORGK e LILAS.
SELECT * FROM pedidos 
WHERE clientID IN ('QUICK', 'MORGK', 'LILAS');

-- 3.3) Listar os detalhes de pedidos que não foram adquiridos pelos produtos 11, 57 e 35.
SELECT * FROM detalhes_pedido 
WHERE produtoID NOT IN (11, 57, 35);

-- SEÇÃO 4: Filtros Específicos e Datas

-- 4.1) Listar todos os clientes que possuem CEP e cujo telefone começa com (011).
SELECT * FROM clientes 
WHERE cep IS NOT NULL 
  AND telefone LIKE '(011)%';

-- 4.2) Listar os pedidos realizados no primeiro dia de cada mês.
SELECT * FROM pedidos 
WHERE DAY dataPedido LIKE '___-__-01';

-- SEÇÃO 5: Junções (JOINS)

-- 5.1 – Listar os nomes dos clientes e a data dos seus pedidos.
SELECT c.nome, p.dataPedido
FROM clientes c, pedidos p
WHERE c.clientID = p.clientID;

-- 5.2 – Listar os nomes dos clientes, a data dos seus pedidos e os itens de pedidos.
SELECT c.nome, p.dataPedido, dp.*
FROM clientes c, pedidos p, detalhes_pedido dp
WHERE c.clienteID = p.clienteID AND p.pedidoID = dp.pedidoID;

-- 5.3 – Listar os nomes dos clientes, a data dos seus pedidos, os itens de pedidos e os nomes dos produtos.
SELECT c.nome, p.dataPedido, dp.*, pr.produtoNome
FROM clientes c, pedidos p, detalhes_pedido dp, produtos pr, categorias cat
WHERE c.clienteID = p.clienteID AND p.pedidoID = dp.pedidoID AND dp.produtoID = pr.produtoID;

-- 5.4 – Listar os nomes dos clientes, a data dos seus pedidos, os itens, produtos e o nome das categorias.
SELECT c.nome, p.dataPedido, dp.*, pr.produtoNome, cat.categoria
FROM clientes c, pedidos p, detalhes_pedido dp, produtos pr, categorias cat
WHERE c.clienteID = p.clienteID AND p.pedidoID = dp.pedidoID AND dp.produtoID = pr.produtoID AND pr.categoriaID = cat.categoriaID;

-- 5.5
SELECT *
FROM clientes c, pedidos p, detalhes_pedido dp
WHERE c.clienteID = p.clienteID
  AND p.pedidoID = dp.pedidoID
  AND c.nome = 'Maria Anders'
  AND p.dataPedido LIKE '1997%';

-- Lista 6
-- 6.1
SELECT nome
FROM clientes
WHERE clienteID IN (
    SELECT p.clienteID
    FROM pedidos p, detalhes_pedido dp, produtos pr
    WHERE p.pedidoID = dp.pedidoID AND dp.produtoID = pr.produtoID AND pr.preco = 1.99
);

-- 6.2
SELECT DISTINCT c.*
FROM categorias c
WHERE c.categoriaID IN (
    SELECT pr.categoriaID
    FROM produtos pr, detalhes_pedido dp
    WHERE pr.produtoID = dp.produtoID AND dp.desconto > 0
);

-- 6.3
SELECT DISTINCT pr.*
FROM produtos pr
WHERE pr.produtoID IN (
    SELECT dp.produtoID
    FROM detalhes_pedido dp, pedidos p
    WHERE dp.pedidoID = p.pedidoID AND p.dataPedido LIKE '1998%'
);

-- Lista 7
-- 7.1
SELECT *
FROM produtos
WHERE preco >= ALL (SELECT preco FROM produtos);

-- 7.2
SELECT *
FROM detalhes_pedido
WHERE quantidade >= ALL (SELECT quantidade FROM detalhes_pedido);

-- 7.3
SELECT *
FROM pedidos
WHERE dataPedido <= ALL (SELECT dataPedido FROM pedidos);

-- Lista 8
-- 8.1
SELECT *
FROM produtos
WHERE preco = 1.99
ORDER BY produtoNome;

-- 8.2
SELECT dp.*, (dp.preco * dp.quantidade) AS precoFinal
FROM detalhes_pedido dp
ORDER BY precoFinal;

