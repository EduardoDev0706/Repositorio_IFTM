-- 1) Criação da nova coluna ano_post, update, criação de índice, profiling e EXPLAIN
SET SQL_SAFE_UPDATES = 0;

ALTER TABLE posts
ADD COLUMN ano_post INT;

UPDATE posts
SET ano_post = YEAR(CreationDate);

CREATE INDEX idx_ano_post
ON posts(ano_post);

SELECT COUNT(*),
       ano_post
FROM posts
GROUP BY ano_post;

SHOW PROFILE FOR QUERY 1;
SHOW PROFILE FOR QUERY 2;

EXPLAIN
SELECT COUNT(*),
       ano_post
FROM posts
GROUP BY ano_post;


-- 2) EXPLAIN para consultas envolvendo chaves primárias usando IN
EXPLAIN
SELECT *
FROM posts
WHERE Id IN (10, 20, 30, 40, 50);


-- 3) Comparação de desempenho usando IN e EXISTS
EXPLAIN
SELECT *
FROM posts
WHERE Id IN (
    SELECT pt.PostId
    FROM PostTags pt
    INNER JOIN Tags t ON t.Id = pt.TagId
    WHERE t.Nome = 'directory-traversal'
);

EXPLAIN
SELECT *
FROM posts p
WHERE EXISTS (
    SELECT 1
    FROM posttags pt
    INNER JOIN Tags t ON t.Id = pt.TagId
    WHERE pt.PostId = p.Id
      AND t.Nome = 'directory-traversal'
);

SET profiling = 1;

SELECT *
FROM posts
WHERE Id IN (
    SELECT pt.PostId
    FROM PostTags pt
    INNER JOIN Tags t ON t.Id = pt.TagId
    WHERE t.Nome = 'directory-traversal'
);

SELECT *
FROM posts p
WHERE EXISTS (
    SELECT 1
    FROM PostTags pt
    INNER JOIN Tags t ON t.Id = pt.TagId
    WHERE pt.PostId = p.Id
      AND t.Nome = 'directory-traversal'
);

SHOW PROFILES;


-- 4) Criação de índice parcial para title(100) e análise de performance
CREATE INDEX idx_title_100
ON posts(Title(100));

EXPLAIN
SELECT *
FROM posts
WHERE Title LIKE 'Java%';

EXPLAIN
SELECT *
FROM posts
WHERE Title LIKE '%Java%';

SHOW INDEX FROM posts;


-- 5) Análise da ordem das colunas em índices compostos e limpeza
CREATE INDEX idx_owner_date
ON posts(OwnerUserId, CreationDate);

CREATE INDEX idx_date_owner
ON posts(CreationDate, OwnerUserId);

EXPLAIN
SELECT *
FROM posts
WHERE OwnerUserId = 123
ORDER BY CreationDate DESC;

ALTER TABLE posts
DROP INDEX idx_owner_date;

ALTER TABLE posts
DROP INDEX idx_date_owner;

SHOW INDEX FROM posts;