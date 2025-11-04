-- ===================================================
-- VERIFICAR DADOS NO POSTGRESQL
-- Execute este script no Query Tool do pgAdmin
-- ===================================================

-- 1. Ver todas as pessoas cadastradas
SELECT * FROM pessoas ORDER BY id DESC;

-- 2. Ver todos os contatos cadastrados
SELECT * FROM contatos ORDER BY id DESC;

-- 3. Contar quantos registros existem
SELECT
    'Pessoas' as tabela,
    COUNT(*) as total
FROM pessoas
UNION ALL
SELECT
    'Contatos' as tabela,
    COUNT(*) as total
FROM contatos;

-- 4. Ver os últimos 5 registros de cada tabela
SELECT 'ÚLTIMAS PESSOAS:' as info;
SELECT * FROM pessoas ORDER BY id DESC LIMIT 5;

SELECT 'ÚLTIMOS CONTATOS:' as info;
SELECT * FROM contatos ORDER BY id DESC LIMIT 5;

