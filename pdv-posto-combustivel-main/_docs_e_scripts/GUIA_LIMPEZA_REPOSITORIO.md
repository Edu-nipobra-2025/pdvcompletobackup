# 🧹 LIMPEZA DO REPOSITÓRIO - GUIA COMPLETO

## 📋 O QUE FOI FEITO:

### 1. ✅ Atualizado `.gitignore` para ignorar:
- Arquivos `.iml` (IntelliJ IDEA)
- Pastas `target/` (Maven)
- Arquivos `.class` compilados
- Arquivos temporários (`.tmp`, `.bak`, `.swp`)
- Arquivos do sistema (`.DS_Store`, `Thumbs.db`)
- **IMPORTANTE**: Arquivos `application.properties` com dados sensíveis

### 2. 🗑️ Arquivos que serão REMOVIDOS do Git (mas permanecem no seu PC):
- `pdv-posto-combustivel-parent.iml` (arquivo do IntelliJ na raiz)
- `pdv-posto-combustivel/pdvpostocombustivel.iml`
- `JavaPoo-Front-End-main/pdv-frontend.iml`
- Pastas `target/` (arquivos compilados do Maven)

### 3. 📦 Alterações de código incluídas no commit:
- ✅ Remoção do campo `numero_ctps` (backend e frontend)
- ✅ Adição do campo `role` (FRENTISTA, USUÁRIO, ADMIN)
- ✅ Correção dos DTOs e Services

---

## 🚀 COMO EXECUTAR:

### Opção 1 - Automática (RECOMENDADO):
Execute o arquivo:
```
_docs_e_scripts\LIMPAR_E_COMMIT.bat
```

### Opção 2 - Manual:
Execute estes comandos no terminal:

```bash
cd "C:\Users\sidney\Documents\faculdades 2025\pdvpostocombustivel"

# Remover .iml do Git (mas manter no PC)
git rm --cached pdv-posto-combustivel-parent.iml
git rm --cached pdv-posto-combustivel/pdvpostocombustivel.iml
git rm --cached JavaPoo-Front-End-main/pdv-frontend.iml

# Remover pastas target
git rm -r --cached pdv-posto-combustivel/target
git rm -r --cached JavaPoo-Front-End-main/target

# Adicionar todas as alterações
git add .

# Fazer commit
git commit -m "Limpeza: Remover arquivos .iml e target + Remover numero_ctps + Adicionar role"

# Enviar para o GitHub
git push
```

---

## ⚠️ IMPORTANTE - ANTES DE FAZER O COMMIT:

### 1️⃣ Execute o SQL no PostgreSQL:
```sql
-- Adicionar coluna role
ALTER TABLE pessoas ADD COLUMN IF NOT EXISTS role VARCHAR(50);
UPDATE pessoas SET role = 'USUÁRIO' WHERE role IS NULL;

-- Remover coluna numero_ctps
ALTER TABLE pessoas DROP COLUMN IF EXISTS numero_ctps;
```

### 2️⃣ Recompile o backend:
```bash
cd pdv-posto-combustivel
mvn clean install -DskipTests
```

---

## 🎯 RESULTADO ESPERADO:

Depois do commit e push, o seu repositório no GitHub:
- ❌ NÃO mostrará mais arquivos `.iml` na página inicial
- ❌ NÃO incluirá pastas `target/` com arquivos compilados
- ✅ Ficará mais limpo e profissional
- ✅ Terá apenas o código-fonte essencial

---

## 📁 ESTRUTURA LIMPA DO REPOSITÓRIO:

```
pdvpostocombustivel/
├── .gitignore (atualizado)
├── pom.xml
├── README.md
├── _docs_e_scripts/
│   ├── Scripts SQL
│   └── Documentação
├── pdv-posto-combustivel/
│   ├── src/
│   └── pom.xml
└── JavaPoo-Front-End-main/
    ├── src/
    └── pom.xml
```

---

## ✅ CHECKLIST ANTES DO PUSH:

- [ ] Executei o SQL no PostgreSQL
- [ ] Recompilei o backend com sucesso
- [ ] Testei criar uma pessoa com função FRENTISTA
- [ ] Verifiquei que está funcionando corretamente
- [ ] Executei o script LIMPAR_E_COMMIT.bat
- [ ] Pronto para fazer `git push`

---

## 🔐 SEGURANÇA:

O `.gitignore` agora também bloqueia:
- Arquivos `.env` (variáveis de ambiente)
- `application.properties` com senhas/configurações sensíveis
- Arquivos de banco de dados local

**Nunca mais envie senhas para o GitHub por engano!** 🔒

