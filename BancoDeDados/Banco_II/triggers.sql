CREATE TABLE IF NOT EXISTS produto_backup (
    id INT,
    nome VARCHAR(100),
    preco DECIMAL(10, 2),
    data_exclusao DATETIME
);

DELIMITER //

CREATE TRIGGER trg_backup_produto_excluido
AFTER DELETE ON produtos
FOR EACH ROW 
BEGIN
    INSERT INTO produto_backup (id, nome, preco, data_exclusao)
    VALUES (OLD.ProdutoID, OLD.ProdutoNome, OLD.preco, NOW());
END //

DELIMITER ;
DELIMITER //

CREATE TRIGGER trg_valida_desconto_item
BEFORE INSERT ON detalhes_pedido
FOR EACH ROW 
BEGIN
    IF NEW.desconto > NEW.precoVenda THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'ERRO: O desconto não pode ser maior que o preço de venda do produto.';
    END IF;
END //

DELIMITER ;