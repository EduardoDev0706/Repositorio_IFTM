DROP PROCEDURE IF EXISTS InserirProduto;

DELIMITER //

CREATE PROCEDURE InserirProduto(
    IN p_ProdutoNome VARCHAR(60),
    IN p_CategoriaID INT,
    IN p_preco DOUBLE,
    IN p_UnidadesEmEstoque SMALLINT,
    IN p_Imagem VARCHAR(100)
)
BEGIN
    DECLARE v_categoria_existe INT DEFAULT 0;

    -- Validação 1: O preço deve ser maior que zero
    IF p_preco <= 0 THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Erro: O preço do produto deve ser maior que zero.';
    END IF;

    -- Validação 2: Verificar se a categoria foi informada e se existe
    IF p_CategoriaID IS NULL THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Erro: A categoria não foi informada.';
    ELSE
        -- Conta quantos registros existem com esse ID na tabela categorias
        SELECT COUNT(*) INTO v_categoria_existe 
        FROM categorias 
        WHERE CategoriaID = p_CategoriaID;
        
        IF v_categoria_existe = 0 THEN
            SIGNAL SQLSTATE '45000' 
            SET MESSAGE_TEXT = 'Erro: A categoria informada não existe.';
        END IF;
    END IF;

    -- Se passou por todas as validações, insere o produto
    INSERT INTO produtos (ProdutoNome, CategoriaID, preco, UnidadesEmEstoque, Imagem)
    VALUES (p_ProdutoNome, p_CategoriaID, p_preco, p_UnidadesEmEstoque, p_Imagem);

END //
DELIMITER ;

DROP PROCEDURE IF EXISTS ListarPrioridadeClientes;

DELIMITER //

CREATE PROCEDURE ListarPrioridadeClientes()
BEGIN
    SELECT 
        ClienteID,
        nome,
        pais,
        CASE 
            WHEN pais = 'Brazil' THEN 'Alta'
            WHEN pais = 'Germany' THEN 'Média'
            ELSE 'Baixa'
        END AS Prioridade_Atendimento
    FROM clientes;
END //
DELIMITER ;

DROP PROCEDURE IF EXISTS InserirClienteUnico;

DELIMITER //

CREATE PROCEDURE InserirClienteUnico(
    IN p_ClienteID CHAR(5),
    IN p_nome VARCHAR(30)
)
BEGIN
    DECLARE v_cliente_existe INT DEFAULT 0;

    -- Verifica se o ClienteID já existe na tabela
    SELECT COUNT(*) INTO v_cliente_existe 
    FROM clientes 
    WHERE ClienteID = p_ClienteID;

    IF v_cliente_existe > 0 THEN
        SELECT 'Cliente já cadastrado' AS Mensagem;
    ELSE
        INSERT INTO clientes (ClienteID, nome) 
        VALUES (p_ClienteID, p_nome);
        
        SELECT 'Cliente inserido com sucesso' AS Mensagem;
    END IF;

END //
DELIMITER ;

-- ALTER TABLE pedidos ADD COLUMN codigo_rastreamento VARCHAR(20);
DROP PROCEDURE IF EXISTS GerarCodigoRastreamento;

DELIMITER //

CREATE PROCEDURE GerarCodigoRastreamento()
BEGIN
    UPDATE pedidos
    SET codigo_rastreamento = CONCAT('RASTREIO-', LPAD(PedidoID, 6, '0'));
    
    SELECT 'Códigos de rastreamento gerados com sucesso para todos os pedidos!' AS Mensagem;
END //
DELIMITER ;

DROP TABLE IF EXISTS log_alteracoes_cliente;

CREATE TABLE log_alteracoes_cliente (
    id_log INT AUTO_INCREMENT PRIMARY KEY,
    ClienteID CHAR(5),
    campo_alterado VARCHAR(50),
    valor_antigo VARCHAR(255),
    valor_novo VARCHAR(255),
    data_alteracao DATETIME,
    FOREIGN KEY (ClienteID) REFERENCES clientes(ClienteID)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP PROCEDURE IF EXISTS AtualizarClienteComAuditoria;

DELIMITER //

CREATE PROCEDURE AtualizarClienteComAuditoria(
    IN p_ClienteID CHAR(5),
    IN p_campo VARCHAR(50),
    IN p_valor_novo VARCHAR(255)
)
BEGIN
    -- Variáveis de sessão (@) para usar com Prepared Statements
    SET @v_ClienteID = p_ClienteID;
    SET @v_valor_novo = p_valor_novo;

    -- 1. Recuperar o valor antigo dinamicamente
    SET @sql_get = CONCAT('SELECT ', p_campo, ' INTO @v_valor_antigo FROM clientes WHERE ClienteID = ?');
    PREPARE stmt_get FROM @sql_get;
    EXECUTE stmt_get USING @v_ClienteID;
    DEALLOCATE PREPARE stmt_get;

    -- 2. Atualizar o campo no cliente dinamicamente
    SET @sql_upd = CONCAT('UPDATE clientes SET ', p_campo, ' = ? WHERE ClienteID = ?');
    PREPARE stmt_upd FROM @sql_upd;
    EXECUTE stmt_upd USING @v_valor_novo, @v_ClienteID;
    DEALLOCATE PREPARE stmt_upd;

    -- 3. Registrar a alteração na tabela de log (auditoria)
    INSERT INTO log_alteracoes_cliente (
        ClienteID, 
        campo_alterado, 
        valor_antigo, 
        valor_novo, 
        data_alteracao
    ) VALUES (
        p_ClienteID, 
        p_campo, 
        @v_valor_antigo, 
        p_valor_novo, 
        NOW()
    );

END //
DELIMITER ;

-- Bloco de Testes

-- Teste 1: Tentar inserir um produto com erro (preço 0) e depois um correto
-- CALL InserirProduto('Produto Erro', 1, 0, 10, 'erro.jpg'); -- Descomente para ver o erro
CALL InserirProduto('Novo Produto Top', 1, 150.50, 10, 'imagem.jpg');

-- Teste 2: Listar prioridades
CALL ListarPrioridadeClientes();

-- Teste 3: Inserir cliente único
CALL InserirClienteUnico('TEST1', 'Cliente de Teste');
CALL InserirClienteUnico('TEST1', 'Cliente de Teste'); -- A 2ª vez deve retornar aviso

-- Teste 4: Gerar Rastrei e visualizar
SET SQL_SAFE_UPDATES = 0; -- Desliga a Trava de Segurança (ERROR 1175)

CALL GerarCodigoRastreamento();

SET SQL_SAFE_UPDATES = 1; -- Religa a Trava de Segurança (Boa Prática)

SELECT PedidoID, codigo_rastreamento FROM pedidos LIMIT 5;

-- Teste 5: Atualizar com Auditoria e visualizar o log
CALL AtualizarClienteComAuditoria('ALFKI', 'nome', 'Maria Silva Atualizada');
SELECT * FROM log_alteracoes_cliente;