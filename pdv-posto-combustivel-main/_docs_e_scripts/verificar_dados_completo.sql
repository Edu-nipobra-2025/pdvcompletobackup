-- Script para verificar todos os dados no banco PostgreSQL

-- Verificar todas as tabelas existentes
SELECT table_name
FROM information_schema.tables
WHERE table_schema = 'public'
ORDER BY table_name;

-- ========================================
-- VERIFICAR PESSOAS
-- ========================================
SELECT COUNT(*) as total_pessoas FROM pessoa;
SELECT * FROM pessoa ORDER BY id DESC;

-- ========================================
-- VERIFICAR CONTATOS
-- ========================================
SELECT COUNT(*) as total_contatos FROM contato;
SELECT * FROM contato ORDER BY id DESC;

-- ========================================
-- VERIFICAR PRODUTOS
-- ========================================
SELECT COUNT(*) as total_produtos FROM produto;
SELECT * FROM produto ORDER BY id DESC;

-- ========================================
-- VERIFICAR PREÇOS
-- ========================================
SELECT COUNT(*) as total_precos FROM preco;
SELECT * FROM preco ORDER BY id DESC;

-- ========================================
-- VERIFICAR CUSTOS
-- ========================================
SELECT COUNT(*) as total_custos FROM custo;
SELECT * FROM custo ORDER BY id DESC;

-- ========================================
-- VERIFICAR ESTOQUES
-- ========================================
SELECT COUNT(*) as total_estoques FROM estoque;
SELECT * FROM estoque ORDER BY id DESC;

-- ========================================
-- VERIFICAR ACESSOS
-- ========================================
SELECT COUNT(*) as total_acessos FROM acesso;
SELECT * FROM acesso ORDER BY id DESC;

-- ========================================
-- VERIFICAR RELACIONAMENTOS (se existirem)
-- ========================================
-- Contatos com suas Pessoas
SELECT
    c.id as contato_id,
    c.email,
    c.telefone,
    p.id as pessoa_id,
    p.nome as pessoa_nome
FROM contato c
LEFT JOIN pessoa p ON c.pessoa_id = p.id
ORDER BY c.id DESC;

-- Pessoas com seus Contatos
SELECT
    p.id as pessoa_id,
    p.nome,
    p.cpf,
    COUNT(c.id) as quantidade_contatos
FROM pessoa p
LEFT JOIN contato c ON c.pessoa_id = p.id
GROUP BY p.id, p.nome, p.cpf
ORDER BY p.id DESC;

