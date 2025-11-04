# 🔄 INSTRUÇÕES CORRETAS - PGADMIN (2 PASSOS)

## ⚠️ O pgAdmin não aceita o comando `\c` para trocar de banco!

Por isso, dividi em **2 scripts separados**:

---

## 📝 **PASSO 1: Criar o banco de dados**

1. No Query Tool que está aberto, **limpe tudo** (Ctrl+A, Delete)
2. Abra o arquivo: `PASSO1_CRIAR_BANCO.sql`
3. Execute (F5 ou ▶)
4. **Feche esta aba do Query Tool**

---

## 📝 **PASSO 2: Criar tabelas e inserir dados**

1. No painel esquerdo do pgAdmin, clique com **botão direito** em **`Databases`**
2. Clique em **`Refresh`** (ou F5)
3. Você verá o banco **`pdvpostocombustivel`** aparecer
4. Clique com **botão direito** em **`pdvpostocombustivel`** (o banco novo)
5. Selecione **`Query Tool`**
6. Abra o arquivo: `PASSO2_CRIAR_TABELAS_E_DADOS.sql`
7. Execute (F5 ou ▶)
8. Você verá uma tabela mostrando quantos registros foram inseridos em cada tabela

---

## ✅ **Resultado esperado:**

```
Tabela     | Total
-----------|------
Pessoas    | 4
Contatos   | 4
Acessos    | 2
Produtos   | 5
Precos     | 5
Estoque    | 5
Custos     | 3
```

---

## 🚀 **Depois disso:**

1. Compile o backend: `mvn clean package -DskipTests`
2. Execute o backend: `PdvpostocombustivelApplication.java`
3. Execute o frontend: `MainApp.java`
4. **Teste criar uma nova pessoa!**

---

## 🔧 **Se der erro no PASSO 1:**

Cole este comando direto no Query Tool:

```sql
DROP DATABASE IF EXISTS pdvpostocombustivel;
CREATE DATABASE pdvpostocombustivel WITH OWNER = postgres ENCODING = 'UTF8';
```

Execute e depois prossiga para o PASSO 2.
-- Script para recriar o banco de dados do zero
-- Execute este script no pgAdmin

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

-- IMPORTANTE: Agora FECHE esta aba e abra o Query Tool CONECTADO ao banco pdvpostocombustivel
-- Depois execute o script RECRIAR_TABELAS_E_DADOS.sql

