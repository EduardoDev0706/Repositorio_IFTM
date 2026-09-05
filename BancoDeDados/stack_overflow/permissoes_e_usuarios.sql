-- Ex 1) Conectado como root
CREATE USER 'eduardo_carmo'@'%' IDENTIFIED BY '12345';

-- Ex 2) 
GRANT SELECT, INSERT, DELETE ON db_ecommerce.* TO 'eduardo_carmo'@'%';

-- Ex 3)
SHOW GRANTS FOR 'eduardo_carmo'@'%';

SELECT * FROM information_schema.schema_privileges 
WHERE grantee LIKE '%eduardo_carmo%';

-- Ex 4) 
REVOKE ALL PRIVILEGES ON db_ecommerce.* FROM 'eduardo_carmo'@'%';

GRANT SELECT ON db_ecommerce.pedidos TO 'eduardo_carmo'@'%';

SHOW GRANTS FOR 'eduardo_carmo'@'%';

SELECT * FROM information_schema.table_privileges 
WHERE grantee LIKE '%eduardo_carmo%';

-- Ex 5)
CREATE USER 'adm_interdisciplinar'@'%' IDENTIFIED BY '12345';

-- Ex 6) 
GRANT ALL PRIVILEGES ON db_ecommerce.* TO 'adm_interdisciplinar'@'%' WITH GRANT OPTION;

GRANT CREATE USER ON *.* TO 'adm_interdisciplinar'@'%';

SHOW GRANTS FOR 'adm_interdisciplinar'@'%';

-- Ex 7) Conectado como adm_interdisciplinar
CREATE USER 'desenvolvedor'@'%' IDENTIFIED BY '12345';

GRANT SELECT ON db_ecommerce.* TO 'desenvolvedor'@'%';

SHOW GRANTS FOR 'desenvolvedor'@'%';

-- Ex 8) Conectado como root
DROP USER 'adm_interdisciplinar'@'%';