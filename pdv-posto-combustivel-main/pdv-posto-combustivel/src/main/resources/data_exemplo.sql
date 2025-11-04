-- Inserindo pessoas (funcionários e clientes)
INSERT INTO pessoas (nome_completo, cpf_cnpj, numero_ctps, data_nascimento, tipo_pessoa, ativo)
SELECT 'João Silva', '12345678901', 123456789, '1990-05-15', 'FISICA', true
WHERE NOT EXISTS (SELECT 1 FROM pessoas WHERE cpf_cnpj = '12345678901');

INSERT INTO pessoas (nome_completo, cpf_cnpj, numero_ctps, data_nascimento, tipo_pessoa, ativo)
SELECT 'Maria Santos', '98765432101', 987654321, '1988-08-20', 'FISICA', true
WHERE NOT EXISTS (SELECT 1 FROM pessoas WHERE cpf_cnpj = '98765432101');

INSERT INTO pessoas (nome_completo, cpf_cnpj, numero_ctps, data_nascimento, tipo_pessoa, ativo)
SELECT 'Pedro Oliveira', '45678912301', NULL, '1985-03-10', 'FISICA', true
WHERE NOT EXISTS (SELECT 1 FROM pessoas WHERE cpf_cnpj = '45678912301');

INSERT INTO pessoas (nome_completo, cpf_cnpj, numero_ctps, data_nascimento, tipo_pessoa, ativo)
SELECT 'Empresa XYZ Ltda', '12345678000199', NULL, '2000-01-01', 'JURIDICA', true
WHERE NOT EXISTS (SELECT 1 FROM pessoas WHERE cpf_cnpj = '12345678000199');

-- Inserindo contatos
INSERT INTO contatos (pessoa_id, telefone, email, endereco)
SELECT 1, '(11) 98765-4321', 'joao.silva@email.com', 'Rua A, 123'
WHERE NOT EXISTS (SELECT 1 FROM contatos WHERE pessoa_id = 1);

INSERT INTO contatos (pessoa_id, telefone, email, endereco)
SELECT 2, '(11) 91234-5678', 'maria.santos@email.com', 'Rua B, 456'
WHERE NOT EXISTS (SELECT 1 FROM contatos WHERE pessoa_id = 2);

INSERT INTO contatos (pessoa_id, telefone, email, endereco)
SELECT 3, '(11) 95555-9999', 'pedro.oliveira@email.com', 'Rua C, 789'
WHERE NOT EXISTS (SELECT 1 FROM contatos WHERE pessoa_id = 3);

INSERT INTO contatos (pessoa_id, telefone, email, endereco)
SELECT 4, '(11) 3333-4444', 'contato@xyz.com', 'Av. Principal, 1000'
WHERE NOT EXISTS (SELECT 1 FROM contatos WHERE pessoa_id = 4);

-- Inserindo acessos (usuários do sistema)
INSERT INTO acessos (pessoa_id, usuario, senha, nivel_acesso)
SELECT 1, 'joao.silva', 'senha123', 'ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM acessos WHERE usuario = 'joao.silva');

INSERT INTO acessos (pessoa_id, usuario, senha, nivel_acesso)
SELECT 2, 'maria.santos', 'senha123', 'OPERADOR'
WHERE NOT EXISTS (SELECT 1 FROM acessos WHERE usuario = 'maria.santos');

-- Inserindo produtos (combustíveis e produtos de conveniência)
INSERT INTO produtos (nome, descricao, codigo_barras, tipo_produto, unidade_medida, ativo)
SELECT 'Gasolina Comum', 'Gasolina tipo C comum', 'GASCOM001', 'COMBUSTIVEL', 'LITRO', true
WHERE NOT EXISTS (SELECT 1 FROM produtos WHERE codigo_barras = 'GASCOM001');

INSERT INTO produtos (nome, descricao, codigo_barras, tipo_produto, unidade_medida, ativo)
SELECT 'Etanol', 'Etanol Hidratado', 'ETANOL001', 'COMBUSTIVEL', 'LITRO', true
WHERE NOT EXISTS (SELECT 1 FROM produtos WHERE codigo_barras = 'ETANOL001');

INSERT INTO produtos (nome, descricao, codigo_barras, tipo_produto, unidade_medida, ativo)
SELECT 'Diesel S10', 'Diesel S10', 'DIES10001', 'COMBUSTIVEL', 'LITRO', true
WHERE NOT EXISTS (SELECT 1 FROM produtos WHERE codigo_barras = 'DIES10001');

INSERT INTO produtos (nome, descricao, codigo_barras, tipo_produto, unidade_medida, ativo)
SELECT 'Óleo Motor 5W30', 'Óleo lubrificante sintético', 'OLE5W30001', 'LUBRIFICANTE', 'UNIDADE', true
WHERE NOT EXISTS (SELECT 1 FROM produtos WHERE codigo_barras = 'OLE5W30001');

INSERT INTO produtos (nome, descricao, codigo_barras, tipo_produto, unidade_medida, ativo)
SELECT 'Água Mineral 500ml', 'Água mineral sem gás', 'AGU500001', 'CONVENIENCIA', 'UNIDADE', true
WHERE NOT EXISTS (SELECT 1 FROM produtos WHERE codigo_barras = 'AGU500001');

-- Inserindo preços dos produtos
INSERT INTO precos (produto_id, preco_compra, preco_venda, margem_lucro, data_vigencia)
SELECT 1, 4.50, 5.89, 30.89, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM precos WHERE produto_id = 1);

INSERT INTO precos (produto_id, preco_compra, preco_venda, margem_lucro, data_vigencia)
SELECT 2, 3.20, 3.99, 24.69, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM precos WHERE produto_id = 2);

INSERT INTO precos (produto_id, preco_compra, preco_venda, margem_lucro, data_vigencia)
SELECT 3, 3.80, 4.99, 31.32, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM precos WHERE produto_id = 3);

INSERT INTO precos (produto_id, preco_compra, preco_venda, margem_lucro, data_vigencia)
SELECT 4, 25.00, 35.00, 40.00, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM precos WHERE produto_id = 4);

INSERT INTO precos (produto_id, preco_compra, preco_venda, margem_lucro, data_vigencia)
SELECT 5, 0.75, 2.50, 233.33, CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM precos WHERE produto_id = 5);

-- Inserindo estoque inicial
INSERT INTO estoque (produto_id, quantidade, data_ultima_movimentacao, tipo_movimentacao)
SELECT 1, 5000.00, CURRENT_TIMESTAMP, 'ENTRADA'
WHERE NOT EXISTS (SELECT 1 FROM estoque WHERE produto_id = 1);

INSERT INTO estoque (produto_id, quantidade, data_ultima_movimentacao, tipo_movimentacao)
SELECT 2, 3000.00, CURRENT_TIMESTAMP, 'ENTRADA'
WHERE NOT EXISTS (SELECT 1 FROM estoque WHERE produto_id = 2);

INSERT INTO estoque (produto_id, quantidade, data_ultima_movimentacao, tipo_movimentacao)
SELECT 3, 4000.00, CURRENT_TIMESTAMP, 'ENTRADA'
WHERE NOT EXISTS (SELECT 1 FROM estoque WHERE produto_id = 3);

INSERT INTO estoque (produto_id, quantidade, data_ultima_movimentacao, tipo_movimentacao)
SELECT 4, 50.00, CURRENT_TIMESTAMP, 'ENTRADA'
WHERE NOT EXISTS (SELECT 1 FROM estoque WHERE produto_id = 4);

INSERT INTO estoque (produto_id, quantidade, data_ultima_movimentacao, tipo_movimentacao)
SELECT 5, 100.00, CURRENT_TIMESTAMP, 'ENTRADA'
WHERE NOT EXISTS (SELECT 1 FROM estoque WHERE produto_id = 5);
