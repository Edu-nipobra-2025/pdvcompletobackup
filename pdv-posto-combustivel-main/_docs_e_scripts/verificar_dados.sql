-- Script para verificar os dados no banco PostgreSQL
-- Execute este arquivo no pgAdmin ou H2 Console

-- ============================================
-- 1. LISTAR TODAS AS TABELAS
-- ============================================
SELECT table_name
FROM information_schema.tables
WHERE table_schema = 'public'
ORDER BY table_name;

-- ============================================
-- 2. CONTAR REGISTROS EM CADA TABELA
-- ============================================
SELECT 'acessos' AS tabela, COUNT(*) AS total_registros FROM acessos
UNION ALL
SELECT 'contatos', COUNT(*) FROM contatos
UNION ALL
SELECT 'custos', COUNT(*) FROM custos
UNION ALL
SELECT 'estoques', COUNT(*) FROM estoques
UNION ALL
SELECT 'pessoas', COUNT(*) FROM pessoas
UNION ALL
SELECT 'precos', COUNT(*) FROM precos
UNION ALL
SELECT 'produtos', COUNT(*) FROM produtos;

-- ============================================
-- 3. VER DADOS DE CADA TABELA
-- ============================================

-- ACESSOS
SELECT 'ACESSOS:' AS info;
SELECT * FROM acessos ORDER BY id;

-- CONTATOS
SELECT 'CONTATOS:' AS info;
SELECT * FROM contatos ORDER BY id;

-- CUSTOS
SELECT 'CUSTOS:' AS info;
SELECT * FROM custos ORDER BY id;

-- ESTOQUES
SELECT 'ESTOQUES:' AS info;
SELECT * FROM estoques ORDER BY id;

-- PESSOAS
SELECT 'PESSOAS:' AS info;
SELECT * FROM pessoas ORDER BY id;

-- PREÇOS
SELECT 'PREÇOS:' AS info;
SELECT * FROM precos ORDER BY id;

-- PRODUTOS
SELECT 'PRODUTOS:' AS info;
SELECT * FROM produtos ORDER BY id;

-- ============================================
-- 4. ESTRUTURA DAS TABELAS
-- ============================================
SELECT
    table_name,
    column_name,
    data_type,
    is_nullable
FROM information_schema.columns
WHERE table_schema = 'public'
ORDER BY table_name, ordinal_position;

