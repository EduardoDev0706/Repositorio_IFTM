-- 1) Procedure: registrar_pedido
DELIMITER //

CREATE PROCEDURE registrar_pedido(
    IN p_cliente_id VARCHAR(50),
    IN p_itens_json JSON
)
main_proc: BEGIN
    -- Declaração de variáveis
    DECLARE v_pedido_id INT;
    DECLARE v_produto_id INT;
    DECLARE v_qtd INT;
    DECLARE v_preco DECIMAL(10,2);
    DECLARE v_desconto DECIMAL(10,2);
    DECLARE v_estoque INT;
    DECLARE v_cliente_existe INT;
    DECLARE v_produto_existe INT;
    
    DECLARE v_idx INT DEFAULT 0;
    DECLARE v_len INT;

    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
        SELECT 'Erro de banco de dados: Transação cancelada.' AS Mensagem;
    END;

    START TRANSACTION;

    -- 1. Valida a existência do ClienteID
    SELECT COUNT(*) INTO v_cliente_existe FROM clientes WHERE ClienteID = p_cliente_id;
    IF v_cliente_existe = 0 THEN
        ROLLBACK;
        SELECT 'Falha: ClienteID inexistente.' AS Mensagem;
        LEAVE main_proc;
    END IF;

    -- 2. Insere a capa do pedido na tabela pedidos
    INSERT INTO pedidos (ClienteID, DataPedido) VALUES (p_cliente_id, NOW());
    SET v_pedido_id = LAST_INSERT_ID(); -- Captura o ID gerado automaticamente

    -- 3. Percorre a lista de itens JSON
    SET v_len = JSON_LENGTH(p_itens_json);
    WHILE v_idx < v_len DO
        -- Extrai os dados do array JSON
        SET v_produto_id = CAST(JSON_UNQUOTE(JSON_EXTRACT(p_itens_json, CONCAT('$[', v_idx, '].ProdutoID'))) AS UNSIGNED);
        SET v_qtd = CAST(JSON_UNQUOTE(JSON_EXTRACT(p_itens_json, CONCAT('$[', v_idx, '].quantidade'))) AS UNSIGNED);
        SET v_preco = CAST(JSON_UNQUOTE(JSON_EXTRACT(p_itens_json, CONCAT('$[', v_idx, '].precoVenda'))) AS DECIMAL(10,2));
        SET v_desconto = CAST(JSON_UNQUOTE(JSON_EXTRACT(p_itens_json, CONCAT('$[', v_idx, '].desconto'))) AS DECIMAL(10,2));

        -- Valida a existência do ProdutoID
        SELECT COUNT(*) INTO v_produto_existe FROM produtos WHERE ProdutoID = v_produto_id;
        IF v_produto_existe = 0 THEN
            ROLLBACK;
            SELECT CONCAT('Falha: ProdutoID ', v_produto_id, ' inexistente.') AS Mensagem;
            LEAVE main_proc;
        END IF;

        -- Valida se o estoque é suficiente e bloqueia a linha para atualização (FOR UPDATE)
        SELECT UnidadesEmEstoque INTO v_estoque FROM produtos WHERE ProdutoID = v_produto_id FOR UPDATE;
        IF v_estoque < v_qtd THEN
            ROLLBACK;
            SELECT CONCAT('Falha: Estoque insuficiente para o ProdutoID ', v_produto_id, '. Disponível: ', v_estoque) AS Mensagem;
            LEAVE main_proc;
        END IF;

        -- 4. Insere o item na tabela detalhes_pedido
        INSERT INTO detalhes_pedido (PedidoID, ProdutoID, Quantidade, PrecoVenda, Desconto)
        VALUES (v_pedido_id, v_produto_id, v_qtd, v_preco, v_desconto);

        -- 5. Atualiza o estoque do produto
        UPDATE produtos SET UnidadesEmEstoque = UnidadesEmEstoque - v_qtd WHERE ProdutoID = v_produto_id;

        SET v_idx = v_idx + 1;
    END WHILE;

    -- Se chegou até aqui sem acionar o LEAVE, tudo ocorreu com sucesso.
    COMMIT;
    SELECT CONCAT('Sucesso! Pedido ', v_pedido_id, ' registrado com sucesso.') AS Mensagem;

END //

DELIMITER ;

-- 2) Procedure: registrar_pedido_savepoint
DELIMITER //

CREATE PROCEDURE registrar_pedido_savepoint(
    IN p_cliente_id VARCHAR(50),
    IN p_itens_json JSON
)
main_proc: BEGIN
    -- Declaração de variáveis
    DECLARE v_pedido_id INT;
    DECLARE v_produto_id INT;
    DECLARE v_qtd INT;
    DECLARE v_preco DECIMAL(10,2);
    DECLARE v_desconto DECIMAL(10,2);
    DECLARE v_estoque INT;
    DECLARE v_cliente_existe INT;
    DECLARE v_produto_existe INT;
    DECLARE v_itens_validos INT DEFAULT 0;
    
    DECLARE v_idx INT DEFAULT 0;
    DECLARE v_len INT;

    -- Handler para erro fatal (Erro na capa do pedido ou erro estrutural)
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
    BEGIN
        ROLLBACK;
        SELECT 'Erro fatal ao criar pedido. Transação inteira cancelada (ROLLBACK).' AS Mensagem;
    END;

    START TRANSACTION;

    -- Valida Cliente (Se falhar, a transação inteira não deve prosseguir)
    SELECT COUNT(*) INTO v_cliente_existe FROM clientes WHERE ClienteID = p_cliente_id;
    IF v_cliente_existe = 0 THEN
        ROLLBACK;
        SELECT 'Falha na Capa: ClienteID inexistente.' AS Mensagem;
        LEAVE main_proc;
    END IF;

    -- Insere Capa do Pedido (Se der erro aqui, o EXIT HANDLER faz o rollback de tudo)
    INSERT INTO pedidos (ClienteID, DataPedido) VALUES (p_cliente_id, NOW());
    SET v_pedido_id = LAST_INSERT_ID();

    -- Percorre os itens com tratamento isolado via SAVEPOINT
    SET v_len = JSON_LENGTH(p_itens_json);
    WHILE v_idx < v_len DO
        SET v_produto_id = CAST(JSON_UNQUOTE(JSON_EXTRACT(p_itens_json, CONCAT('$[', v_idx, '].ProdutoID'))) AS UNSIGNED);
        SET v_qtd = CAST(JSON_UNQUOTE(JSON_EXTRACT(p_itens_json, CONCAT('$[', v_idx, '].quantidade'))) AS UNSIGNED);
        SET v_preco = CAST(JSON_UNQUOTE(JSON_EXTRACT(p_itens_json, CONCAT('$[', v_idx, '].precoVenda'))) AS DECIMAL(10,2));
        SET v_desconto = CAST(JSON_UNQUOTE(JSON_EXTRACT(p_itens_json, CONCAT('$[', v_idx, '].desconto'))) AS DECIMAL(10,2));

        -- Bloco isolado para o item atual
        bloco_item: BEGIN
            -- Handler de erro em nível de bloco para capturar erros de SQL no item
            DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
            BEGIN
                ROLLBACK TO SAVEPOINT sp_item;
            END;

            -- Cria o ponto de restauração para este item específico
            SAVEPOINT sp_item;

            -- Valida o Produto
            SELECT COUNT(*) INTO v_produto_existe FROM produtos WHERE ProdutoID = v_produto_id;
            IF v_produto_existe = 0 THEN
                ROLLBACK TO SAVEPOINT sp_item;
                LEAVE bloco_item;
            END IF;

            -- Valida o Estoque
            SELECT UnidadesEmEstoque INTO v_estoque FROM produtos WHERE ProdutoID = v_produto_id FOR UPDATE;
            IF v_estoque < v_qtd THEN
                ROLLBACK TO SAVEPOINT sp_item;
                LEAVE bloco_item;
            END IF;

            -- Tenta inserir o detalhe e atualizar o estoque
            INSERT INTO detalhes_pedido (PedidoID, ProdutoID, Quantidade, PrecoVenda, Desconto)
            VALUES (v_pedido_id, v_produto_id, v_qtd, v_preco, v_desconto);

            UPDATE produtos SET UnidadesEmEstoque = UnidadesEmEstoque - v_qtd WHERE ProdutoID = v_produto_id;

        END bloco_item;

        SET v_idx = v_idx + 1;
    END WHILE;

    -- Validação Final: Verifica quantos itens foram efetivamente salvos no banco para este pedido
    SELECT COUNT(*) INTO v_itens_validos FROM detalhes_pedido WHERE PedidoID = v_pedido_id;

    IF v_itens_validos > 0 THEN
        COMMIT;
        SELECT CONCAT('Sucesso parcial/total! Pedido ', v_pedido_id, ' finalizado com ', v_itens_validos, ' item(ns) salvo(s).') AS Mensagem;
    ELSE
        -- Se nenhum item sobreviveu às validações, tudo é cancelado.
        ROLLBACK;
        SELECT 'Pedido cancelado: Nenhum item fornecido era válido ou possuía estoque.' AS Mensagem;
    END IF;

END //

DELIMITER ;