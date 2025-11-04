# 🚀 SETUP RÁPIDO - PDV Posto Combustível

## ⚡ Configuração em 5 Minutos

### 1️⃣ Pré-requisitos
- ✅ Java 17 instalado
- ✅ PostgreSQL instalado e rodando
- ✅ Maven instalado
- ✅ IntelliJ IDEA ou outra IDE

### 2️⃣ Configurar o Banco de Dados

**Opção A: Usar pgAdmin**
1. Abra o pgAdmin
2. Conecte no PostgreSQL
3. Execute o script: `_docs_e_scripts/PASSO1_CRIAR_BANCO.sql`
4. Execute o script: `_docs_e_scripts/PASSO2_CRIAR_TABELAS_E_DADOS.sql`

**Opção B: Usar linha de comando**
```bash
psql -U postgres
CREATE DATABASE pdvpostocombustivel;
\c pdvpostocombustivel
\i _docs_e_scripts/PASSO2_CRIAR_TABELAS_E_DADOS.sql
```

### 3️⃣ Configurar a Aplicação

1. Navegue até: `pdv-posto-combustivel/src/main/resources/`
2. Abra o arquivo `application.properties`
3. Configure sua senha do PostgreSQL:

```properties
spring.datasource.password=SUA_SENHA_AQUI
```

### 4️⃣ Executar o Backend

**Opção A: Pelo IntelliJ**
1. Abra o arquivo: `PdvpostocombustivelApplication.java`
2. Clique no botão ▶️ verde ao lado de `public class`

**Opção B: Pelo terminal**
```bash
cd pdv-posto-combustivel
mvn spring-boot:run
```

**Opção C: Usando o .bat (Windows)**
```bash
EXECUTAR_BACKEND.bat
```

### 5️⃣ Executar o Frontend

**Opção A: Pelo IntelliJ**
1. Navegue até: `JavaPoo-Front-End-main/src/main/java/br/com/PdvFrontEnd/view/`
2. Abra o arquivo: `MainApp.java`
3. Clique no botão ▶️ verde

**Opção B: Usando o .bat**
```bash
_docs_e_scripts/EXECUTAR_FRONTEND.bat
```

### 6️⃣ Verificar se funcionou

✅ **Backend rodando:** Acesse http://localhost:8080/swagger-ui.html
✅ **Frontend rodando:** Interface Swing deve abrir
✅ **Banco conectado:** Veja os logs no console sem erros

---

## 🐛 Problemas Comuns

### ❌ Erro: "Port 8080 already in use"
**Solução:**
```bash
# Windows
netstat -ano | findstr :8080
taskkill /PID <numero_do_pid> /F
```

### ❌ Erro: "Connection refused" no banco
**Solução:**
1. Verifique se o PostgreSQL está rodando
2. Confirme usuário e senha em `application.properties`
3. Teste a conexão no pgAdmin primeiro

### ❌ Erro: "ClassNotFoundException"
**Solução:**
```bash
mvn clean install
```

---

## 📦 Para quem baixou do GitHub

1. **Extraia o ZIP** em uma pasta
2. **Configure o banco** (Passo 2 acima)
3. **Edite** `application.properties` com sua senha
4. **Execute** o backend e frontend

**IMPORTANTE:** O arquivo `application.properties` tem a senha em branco por segurança!

---

## 🎯 Próximos Passos

Depois que tudo estiver funcionando:
1. Leia o `GUIA_MELHORIAS.md`
2. Teste as melhorias implementadas
3. Execute os testes: `mvn test`
4. Explore o Swagger para testar a API

---

**Dúvidas?** Veja os arquivos na pasta `_docs_e_scripts/`

