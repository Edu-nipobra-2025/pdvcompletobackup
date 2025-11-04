-- Criação da tabela pessoas
CREATE TABLE IF NOT EXISTS pessoas (
    id BIGSERIAL PRIMARY KEY,
    nome_completo VARCHAR(200) NOT NULL,
    cpf_cnpj VARCHAR(20) NOT NULL UNIQUE,
    numero_ctps BIGINT,
    data_nascimento DATE,
    tipo_pessoa VARCHAR(50) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE
);

-- Criação da tabela contatos
CREATE TABLE IF NOT EXISTS contatos (
    id BIGSERIAL PRIMARY KEY,
    pessoa_id BIGINT NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100),
    endereco VARCHAR(200),
    FOREIGN KEY (pessoa_id) REFERENCES pessoas(id)
);

-- Criação da tabela acessos
CREATE TABLE IF NOT EXISTS acessos (
    id BIGSERIAL PRIMARY KEY,
    pessoa_id BIGINT NOT NULL,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    nivel_acesso VARCHAR(20) NOT NULL,
    ultimo_acesso TIMESTAMP,
    FOREIGN KEY (pessoa_id) REFERENCES pessoas(id)
);

-- Criação da tabela produtos
CREATE TABLE IF NOT EXISTS produtos (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    codigo_barras VARCHAR(50) UNIQUE,
    tipo_produto VARCHAR(50) NOT NULL,
    unidade_medida VARCHAR(20) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE
);

-- Criação da tabela estoque
CREATE TABLE IF NOT EXISTS estoque (
    id BIGSERIAL PRIMARY KEY,
    produto_id BIGINT NOT NULL,
    quantidade DECIMAL(10,2) NOT NULL,
    data_ultima_movimentacao TIMESTAMP NOT NULL,
    tipo_movimentacao VARCHAR(20) NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES produtos(id)
);

-- Criação da tabela precos
CREATE TABLE IF NOT EXISTS precos (
    id BIGSERIAL PRIMARY KEY,
    produto_id BIGINT NOT NULL,
    preco_compra DECIMAL(10,2) NOT NULL,
    preco_venda DECIMAL(10,2) NOT NULL,
    margem_lucro DECIMAL(5,2),
    data_vigencia TIMESTAMP NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES produtos(id)
);

-- Criação da tabela vendas
CREATE TABLE IF NOT EXISTS vendas (
    id BIGSERIAL PRIMARY KEY,
    cliente_id BIGINT,
    funcionario_id BIGINT NOT NULL,
    data_venda TIMESTAMP NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL,
    forma_pagamento VARCHAR(50) NOT NULL,
    status VARCHAR(20) NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES pessoas(id),
    FOREIGN KEY (funcionario_id) REFERENCES pessoas(id)
);

-- Criação da tabela itens_venda
CREATE TABLE IF NOT EXISTS itens_venda (
    id BIGSERIAL PRIMARY KEY,
    venda_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    quantidade DECIMAL(10,2) NOT NULL,
    preco_unitario DECIMAL(10,2) NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (venda_id) REFERENCES vendas(id),
    FOREIGN KEY (produto_id) REFERENCES produtos(id)
);

-- Criação da tabela custos
CREATE TABLE IF NOT EXISTS custos (
    id BIGSERIAL PRIMARY KEY,
    descricao VARCHAR(200) NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    data_lancamento TIMESTAMP NOT NULL,
    tipo_custo VARCHAR(50) NOT NULL,
    observacao TEXT
);
