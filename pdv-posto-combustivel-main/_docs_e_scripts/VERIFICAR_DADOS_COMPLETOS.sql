-- Script para verificar TODOS os dados inseridos recentemente
-- Execute este script no pgAdmin para ver os dados mais recentes

-- Ver todas as pessoas cadastradas
SELECT 'PESSOAS' as tabela, COUNT(*) as total FROM pessoas;
SELECT * FROM pessoas ORDER BY id DESC;

-- Ver todos os contatos cadastrados
SELECT 'CONTATOS' as tabela, COUNT(*) as total FROM contatos;
SELECT * FROM contatos ORDER BY id DESC;

-- Ver todos os produtos cadastrados
SELECT 'PRODUTOS' as tabela, COUNT(*) as total FROM produtos;
SELECT * FROM produtos ORDER BY id DESC;

-- Ver todos os preços cadastrados
SELECT 'PRECOS' as tabela, COUNT(*) as total FROM precos;
SELECT * FROM precos ORDER BY id DESC;

-- Ver todos os custos cadastrados
SELECT 'CUSTOS' as tabela, COUNT(*) as total FROM custos;
SELECT * FROM custos ORDER BY id DESC;

-- Ver todos os estoques cadastrados
SELECT 'ESTOQUES' as tabela, COUNT(*) as total FROM estoques;
SELECT * FROM estoques ORDER BY id DESC;

-- Ver todos os acessos cadastrados
SELECT 'ACESSOS' as tabela, COUNT(*) as total FROM acessos;
SELECT * FROM acessos ORDER BY id DESC;

