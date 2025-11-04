# 🔄 INSTRUÇÕES PARA RECRIAR O BANCO DE DADOS

## ⚠️ IMPORTANTE: Execute estes passos NA ORDEM!

### 📝 Passo 1: Execute o Script SQL no PostgreSQL

1. **Abra o pgAdmin** ou qualquer cliente PostgreSQL
2. **Conecte-se ao servidor PostgreSQL** (localhost)
3. **Abra o Query Tool** (botão direito no servidor PostgreSQL → Query Tool)
4. **Abra o arquivo**: `RECRIAR_BANCO_LIMPO.sql`
5. **Execute o script completo** (F5 ou botão ▶ Run)
6. **Aguarde a mensagem**: "Banco de dados criado com sucesso!"

### ✅ O que o script faz:

- ✅ Desconecta todos os usuários do banco antigo
- ✅ Deleta o banco antigo `pdvpostocombustivel` (se existir)
- ✅ Cria um banco novo limpo
- ✅ Cria todas as 7 tabelas necessárias:
  - pessoas
  - contatos
  - acessos
  - produtos
  - precos
  - estoque
  - custos
- ✅ Insere dados de exemplo (4 pessoas, 4 contatos, 2 acessos, 5 produtos, etc.)
- ✅ Verifica se todos os dados foram inseridos corretamente

### 🚀 Passo 2: Execute o Backend

1. **Feche o backend** se estiver rodando
2. **Execute novamente**: `PdvpostocombustivelApplication.java`
3. **Aguarde a mensagem**: "Started PdvpostocombustivelApplication"

### 🎨 Passo 3: Execute o Frontend

1. **Feche o frontend** se estiver rodando
2. **Execute novamente**: `MainApp.java`
3. **Teste todas as funcionalidades**

### 📊 Passo 4: Verifique os Dados

No pgAdmin, execute esta query para ver os dados:

```sql
SELECT * FROM pessoas;
SELECT * FROM contatos;
SELECT * FROM produtos;
```

### 🎯 O que mudou:

1. **Banco de dados novo** - Sem corrupção ou inconsistências
2. **Tabelas limpas** - Estrutura correta para todas as entidades
3. **Dados de exemplo** - 4 pessoas, 4 contatos, 5 produtos já inseridos
4. **application.properties atualizado** - Não vai tentar inserir dados duplicados

### ⚡ Agora você pode:

- ✅ Criar novas pessoas pelo frontend → Salva no PostgreSQL
- ✅ Criar novos contatos pelo frontend → Salva no PostgreSQL
- ✅ Listar todos os dados → Busca do PostgreSQL
- ✅ Atualizar e deletar → Funciona perfeitamente

### 🔧 Se der erro:

1. Verifique se o PostgreSQL está rodando
2. Verifique se a senha é: `Sidney123@`
3. Verifique se a porta é: `5432`
4. Execute o script SQL novamente

**EXECUTE O PASSO 1 AGORA NO PGADMIN! 🚀**

