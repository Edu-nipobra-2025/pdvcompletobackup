-- Script para recriar o banco de dados do zero
-- Execute este script no pgAdmin ou psql

-- 1. Desconectar todos os usuários do banco (se existir)
SELECT pg_terminate_backend(pg_stat_activity.pid)
FROM pg_stat_activity
WHERE pg_stat_activity.datname = 'pdvpostocombustivel'
  AND pid <> pg_backend_pid();

-- 2. Dropar o banco antigo (se existir)
DROP DATABASE IF EXISTS pdvpostocombustivel;

-- 3. Criar o banco novo
CREATE DATABASE pdvpostocombustivel
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- 4. Conectar ao banco novo
\c pdvpostocombustivel

-- 5. Criar as tabelas
CREATE TABLE pessoas (
    id BIGSERIAL PRIMARY KEY,
    nome_completo VARCHAR(200) NOT NULL,
    cpf_cnpj VARCHAR(20) NOT NULL UNIQUE,
    numero_ctps BIGINT,
    data_nascimento DATE NOT NULL,
    tipo_pessoa VARCHAR(15) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT true
);

CREATE TABLE contatos (
    id BIGSERIAL PRIMARY KEY,
    pessoa_id BIGINT,
    telefone VARCHAR(20),
    email VARCHAR(100),
    endereco VARCHAR(255),
    FOREIGN KEY (pessoa_id) REFERENCES pessoas(id) ON DELETE CASCADE
);

CREATE TABLE acessos (
    id BIGSERIAL PRIMARY KEY,
    pessoa_id BIGINT NOT NULL,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    nivel_acesso VARCHAR(20) NOT NULL,
    FOREIGN KEY (pessoa_id) REFERENCES pessoas(id) ON DELETE CASCADE
);

CREATE TABLE produtos (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    referencia VARCHAR(50),
    fornecedor VARCHAR(100),
    categoria VARCHAR(50),
    marca VARCHAR(50)
);

CREATE TABLE precos (
    id BIGSERIAL PRIMARY KEY,
    produto_id BIGINT NOT NULL,
    preco_compra DECIMAL(10, 2),
    preco_venda DECIMAL(10, 2),
    data_vigencia TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (produto_id) REFERENCES produtos(id) ON DELETE CASCADE
);

CREATE TABLE estoque (
    id BIGSERIAL PRIMARY KEY,
    produto_id BIGINT NOT NULL,
    quantidade DECIMAL(10, 2) NOT NULL DEFAULT 0,
    data_ultima_movimentacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (produto_id) REFERENCES produtos(id) ON DELETE CASCADE
);

CREATE TABLE custos (
    id BIGSERIAL PRIMARY KEY,
    produto_id BIGINT NOT NULL,
    descricao VARCHAR(255),
    valor DECIMAL(10, 2) NOT NULL,
    data_custo DATE NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES produtos(id) ON DELETE CASCADE
);

-- 6. Inserir dados de exemplo
INSERT INTO pessoas (nome_completo, cpf_cnpj, numero_ctps, data_nascimento, tipo_pessoa, ativo) VALUES
('João Silva', '12345678901', 123456789, '1990-05-15', 'FISICA', true),
('Maria Santos', '98765432101', 987654321, '1988-08-20', 'FISICA', true),
('Pedro Oliveira', '45678912301', NULL, '1985-03-10', 'FISICA', true),
('Empresa XYZ Ltda', '12345678000199', NULL, '2000-01-01', 'JURIDICA', true);

INSERT INTO contatos (pessoa_id, telefone, email, endereco) VALUES
(1, '(11) 98765-4321', 'joao.silva@email.com', 'Rua A, 123'),
(2, '(11) 91234-5678', 'maria.santos@email.com', 'Rua B, 456'),
(3, '(11) 95555-9999', 'pedro.oliveira@email.com', 'Rua C, 789'),
(4, '(11) 3333-4444', 'contato@xyz.com', 'Av. Principal, 1000');

INSERT INTO acessos (pessoa_id, usuario, senha, nivel_acesso) VALUES
(1, 'joao.silva', 'senha123', 'ADMIN'),
(2, 'maria.santos', 'senha123', 'OPERADOR');

INSERT INTO produtos (nome, referencia, fornecedor, categoria, marca) VALUES
('Gasolina Comum', 'GASCOM001', 'Petrobras', 'COMBUSTIVEL', 'Petrobras'),
('Etanol', 'ETANOL001', 'Raizen', 'COMBUSTIVEL', 'Shell'),
('Diesel S10', 'DIES10001', 'Ipiranga', 'COMBUSTIVEL', 'Ipiranga'),
('Óleo Motor 5W30', 'OLE5W30001', 'Castrol', 'LUBRIFICANTE', 'Castrol'),
('Água Mineral 500ml', 'AGU500001', 'Nestlé', 'CONVENIENCIA', 'Nestlé');

INSERT INTO precos (produto_id, preco_compra, preco_venda, data_vigencia) VALUES
(1, 4.50, 5.89, CURRENT_TIMESTAMP),
(2, 3.20, 3.99, CURRENT_TIMESTAMP),
(3, 3.80, 4.99, CURRENT_TIMESTAMP),
(4, 25.00, 35.00, CURRENT_TIMESTAMP),
(5, 0.75, 2.50, CURRENT_TIMESTAMP);

INSERT INTO estoque (produto_id, quantidade, data_ultima_movimentacao) VALUES
(1, 5000.00, CURRENT_TIMESTAMP),
(2, 3000.00, CURRENT_TIMESTAMP),
(3, 4000.00, CURRENT_TIMESTAMP),
(4, 50.00, CURRENT_TIMESTAMP),
(5, 100.00, CURRENT_TIMESTAMP);

INSERT INTO custos (produto_id, descricao, valor, data_custo) VALUES
(1, 'Frete de entrega', 150.00, CURRENT_DATE),
(2, 'Taxa de armazenamento', 80.00, CURRENT_DATE),
(3, 'Seguro de transporte', 200.00, CURRENT_DATE);

-- 7. Verificar os dados inseridos
SELECT 'Pessoas' AS tabela, COUNT(*) AS total FROM pessoas
UNION ALL
SELECT 'Contatos', COUNT(*) FROM contatos
UNION ALL
SELECT 'Acessos', COUNT(*) FROM acessos
UNION ALL
SELECT 'Produtos', COUNT(*) FROM produtos
UNION ALL
SELECT 'Precos', COUNT(*) FROM precos
UNION ALL
SELECT 'Estoque', COUNT(*) FROM estoque
UNION ALL
SELECT 'Custos', COUNT(*) FROM custos;

-- Mensagem de sucesso
SELECT 'Banco de dados criado com sucesso!' AS status;

