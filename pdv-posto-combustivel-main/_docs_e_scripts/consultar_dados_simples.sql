-- Consultar todas as pessoas
SELECT * FROM pessoa ORDER BY id DESC LIMIT 10;

-- Consultar todos os contatos
SELECT * FROM contato ORDER BY id DESC LIMIT 10;

-- Contar quantos registros existem
SELECT
    'Pessoas' as tabela,
    COUNT(*) as total
FROM pessoa
UNION ALL
SELECT
    'Contatos' as tabela,
    COUNT(*) as total
FROM contato;

