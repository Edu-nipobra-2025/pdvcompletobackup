-- Remover coluna numero_ctps da tabela pessoas
ALTER TABLE pessoas DROP COLUMN IF EXISTS numero_ctps;

-- Confirmação
SELECT 'Coluna numero_ctps removida com sucesso!' as status;

