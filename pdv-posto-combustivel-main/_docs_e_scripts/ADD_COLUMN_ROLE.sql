-- Adicionar coluna role na tabela pessoas
ALTER TABLE pessoas ADD COLUMN IF NOT EXISTS role VARCHAR(50);

-- Atualizar registros existentes com valor padrão 'USUÁRIO'
UPDATE pessoas SET role = 'USUÁRIO' WHERE role IS NULL;

-- Comentário: Esta coluna armazena a função da pessoa (FRENTISTA, USUÁRIO, ADMIN)

