-- Verificar a estrutura da tabela pessoas
SELECT column_name, data_type, character_maximum_length, is_nullable
FROM information_schema.columns
WHERE table_name = 'pessoas'
ORDER BY ordinal_position;

-- Verificar a estrutura da tabela contatos
SELECT column_name, data_type, character_maximum_length, is_nullable
FROM information_schema.columns
WHERE table_name = 'contatos'
ORDER BY ordinal_position;

-- Consultar dados das tabelas
SELECT * FROM pessoas ORDER BY id DESC LIMIT 10;
SELECT * FROM contatos ORDER BY id DESC LIMIT 10;

-- Contar registros
SELECT 'Pessoas' as tabela, COUNT(*) as total FROM pessoas
UNION ALL
SELECT 'Contatos' as tabela, COUNT(*) as total FROM contatos;

