-- Script para limpar todos os dados do banco de dados pdvpostocombustivel
-- Mantém a estrutura das tabelas, apenas remove os dados

-- Desabilitar verificação de chaves estrangeiras temporariamente
SET session_replication_role = 'replica';

-- Limpar todas as tabelas na ordem correta (respeitando dependências)
TRUNCATE TABLE acessos CASCADE;
TRUNCATE TABLE contatos CASCADE;
TRUNCATE TABLE custos CASCADE;
TRUNCATE TABLE estoques CASCADE;
TRUNCATE TABLE pessoas CASCADE;
TRUNCATE TABLE precos CASCADE;
TRUNCATE TABLE produtos CASCADE;

-- Reabilitar verificação de chaves estrangeiras
SET session_replication_role = 'origin';

-- Resetar as sequences (auto-incremento) para começar do 1 novamente
ALTER SEQUENCE IF EXISTS acessos_id_seq RESTART WITH 1;
ALTER SEQUENCE IF EXISTS contatos_id_seq RESTART WITH 1;
ALTER SEQUENCE IF EXISTS custos_id_seq RESTART WITH 1;
ALTER SEQUENCE IF EXISTS estoques_id_seq RESTART WITH 1;
ALTER SEQUENCE IF EXISTS pessoas_id_seq RESTART WITH 1;
ALTER SEQUENCE IF EXISTS precos_id_seq RESTART WITH 1;
ALTER SEQUENCE IF EXISTS produtos_id_seq RESTART WITH 1;

-- Verificar se as tabelas estão vazias
SELECT 'Tabela acessos tem ' || COUNT(*) || ' registros' FROM acessos;
SELECT 'Tabela contatos tem ' || COUNT(*) || ' registros' FROM contatos;
SELECT 'Tabela custos tem ' || COUNT(*) || ' registros' FROM custos;
SELECT 'Tabela estoques tem ' || COUNT(*) || ' registros' FROM estoques;
SELECT 'Tabela pessoas tem ' || COUNT(*) || ' registros' FROM pessoas;
SELECT 'Tabela precos tem ' || COUNT(*) || ' registros' FROM precos;
SELECT 'Tabela produtos tem ' || COUNT(*) || ' registros' FROM produtos;

-- Mensagem de confirmação
SELECT '✓ Banco de dados limpo com sucesso! Todas as tabelas estão vazias.' AS status;
