# 🗑️ REMOVER COLUNA numero_ctps

## 📋 INSTRUÇÕES PARA REMOVER A COLUNA

### Via pgAdmin (RECOMENDADO)

1. **Abra o pgAdmin**

2. **Conecte ao banco de dados:**
   - Servidor: `localhost` ou `PostgreSQL 15`
   - Banco: `pdv_posto_combustivel`

3. **Abra o Query Tool:**
   - Clique com botão direito no banco `pdv_posto_combustivel`
   - Selecione: `Query Tool`

4. **Cole e execute este comando:**

```sql
-- Remover coluna numero_ctps da tabela pessoas
ALTER TABLE pessoas DROP COLUMN IF EXISTS numero_ctps;

-- Confirmar remoção
SELECT 'Coluna numero_ctps removida com sucesso!' as status;

-- Verificar estrutura da tabela
SELECT column_name, data_type 
FROM information_schema.columns 
WHERE table_name = 'pessoas' 
ORDER BY ordinal_position;
```

5. **Clique em:** ▶️ (Execute/Refresh) ou pressione `F5`

6. **Resultado esperado:**
```
status
─────────────────────────────────────────
Coluna numero_ctps removida com sucesso!
```

---

## ✅ VERIFICAR SE FOI REMOVIDA

Execute este comando para ver as colunas da tabela `pessoas`:

```sql
SELECT column_name, data_type 
FROM information_schema.columns 
WHERE table_name = 'pessoas' 
ORDER BY ordinal_position;
```

**Resultado esperado (SEM numero_ctps):**
```
    column_name     │     data_type      
────────────────────┼───────────────────
 id                 │ bigint
 ativo              │ boolean
 cpf_cnpj           │ character varying
 data_nascimento    │ date
 nome_completo      │ character varying
 tipo_pessoa        │ character varying
 role               │ character varying
```

**✅ A coluna `numero_ctps` NÃO deve aparecer na lista!**

---

## 🔄 ALTERNATIVA: Via DBeaver

Se você usa DBeaver:

1. Conecte ao banco `pdv_posto_combustivel`
2. Abra `SQL Editor` (Ctrl + ])
3. Cole o comando SQL acima
4. Execute (Ctrl + Enter)

---

## 📝 SCRIPT COMPLETO

O script completo está em:
```
_docs_e_scripts/REMOVER_COLUNA_NUMERO_CTPS.sql
```

Você pode abrir este arquivo no pgAdmin e executar diretamente!

---

## ⚠️ IMPORTANTE

Após remover a coluna:

1. ✅ O backend continuará funcionando (não usa numero_ctps)
2. ✅ O frontend continuará funcionando (não usa numero_ctps)
3. ✅ Os dados existentes na tabela são preservados
4. ✅ Apenas a coluna numero_ctps é removida

---

## 🎯 PASSO A PASSO VISUAL

```
1. Abra pgAdmin
        ↓
2. Expanda Servers → PostgreSQL 15
        ↓
3. Expanda Databases → pdv_posto_combustivel
        ↓
4. Clique com direito em pdv_posto_combustivel
        ↓
5. Selecione "Query Tool"
        ↓
6. Cole o comando:
   ALTER TABLE pessoas DROP COLUMN IF EXISTS numero_ctps;
        ↓
7. Clique em ▶️ (Execute)
        ↓
8. Pronto! ✅
```

---

## 🔍 ANTES E DEPOIS

### ANTES (COM numero_ctps):
```
 id │ nome_completo │ cpf_cnpj    │ numero_ctps │ data_nascimento │ ...
────┼───────────────┼─────────────┼─────────────┼─────────────────┼───
  1 │ João Silva    │ 12345678901 │ 123456789   │ 2000-01-01      │ ...
```

### DEPOIS (SEM numero_ctps):
```
 id │ nome_completo │ cpf_cnpj    │ data_nascimento │ tipo_pessoa │ ...
────┼───────────────┼─────────────┼─────────────────┼─────────────┼───
  1 │ João Silva    │ 12345678901 │ 2000-01-01      │ FÍSICA      │ ...
```

---

## 🎊 CONCLUSÃO

Execute o comando SQL no pgAdmin para remover definitivamente a coluna `numero_ctps` do banco de dados!

**Comando:**
```sql
ALTER TABLE pessoas DROP COLUMN IF EXISTS numero_ctps;
```

**Simples e rápido!** ✅

