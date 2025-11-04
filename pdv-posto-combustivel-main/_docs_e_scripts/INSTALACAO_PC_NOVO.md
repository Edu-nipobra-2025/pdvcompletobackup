# 📦 INSTALAÇÃO EM PC NOVO - GUIA RÁPIDO

## ✅ RESUMO PARA INSTALAÇÃO

**Você só precisa fazer 5 passos:**

1. 📥 Extrair o ZIP
2. 🗄️ Criar banco: `pdvpostocombustivel`
3. ⚙️ Configurar senha no `application.properties`
4. 🚀 Executar Backend: `mvn spring-boot:run`
5. 🖥️ Executar Frontend: `Run MainApp.java`

**Tempo estimado:** 10 minutos ⏱️

---

## 📋 PRÉ-REQUISITOS

Antes de começar, verifique se tem instalado:

```powershell
# Java 17+
java -version

# Maven
mvn -version

# PostgreSQL rodando na porta 5432
```

**Não tem?** Instale primeiro:
- Java: https://www.oracle.com/java/technologies/downloads/
- Maven: https://maven.apache.org/download.cgi
- PostgreSQL: https://www.postgresql.org/download/

---

## 🚀 PASSO A PASSO

### 1️⃣ Extrair o ZIP

```
Baixe do GitHub → Download ZIP
Extraia em: C:\projetos\pdv-posto-combustivel
```

### 2️⃣ Criar Banco de Dados

**No pgAdmin:**

```
1. Abra pgAdmin 4
2. Botão direito em "Databases"
3. Create → Database
4. Nome: pdvpostocombustivel
5. Save
```

**Ou via SQL:**
```sql
CREATE DATABASE pdvpostocombustivel;
```

### 3️⃣ Configurar Senha do PostgreSQL

**Localize o arquivo:**
```
pdv-posto-combustivel/src/main/resources/application.properties
```

**Se não existir, copie de:**
```
application.properties.example → application.properties
```

**Edite a senha:**
```properties
spring.datasource.password=SUA_SENHA_AQUI
```

**Exemplo:**
```properties
spring.datasource.password=postgres123
```

### 4️⃣ Executar Backend

**Via terminal:**
```powershell
cd pdv-posto-combustivel
mvn spring-boot:run
```

**Via IntelliJ:**
```
1. Abra: PdvpostocombustivelApplication.java
2. Run (botão verde ▶️)
```

**✅ Sucesso se ver:**
```
Started PdvpostocombustivelApplication in X seconds
```

### 5️⃣ Executar Frontend

**Via IntelliJ:**
```
1. Abra projeto: JavaPoo-Front-End-main
2. Localize: MainApp.java
3. Run 'MainApp.main()'
```

**✅ Sucesso:** Janela do sistema abre!

---

## 🎯 VERIFICAÇÃO RÁPIDA

### ✅ Backend funcionando?

```
Acesse: http://localhost:8080/swagger-ui.html
Deve abrir a documentação da API
```

### ✅ Banco criado corretamente?

**No pgAdmin:**
```
pdvpostocombustivel → Tables
Deve ter 7 tabelas:
- pessoas
- produtos
- precos
- custos
- estoques
- acessos
- contatos
```

### ✅ Frontend funcionando?

```
1. Abra MainApp
2. Faça login/cadastro
3. Clique em "Gerenciar Pessoas"
4. Adicione uma pessoa
5. Verifique no pgAdmin:
   SELECT * FROM pessoas;
```

---

## 🔄 FLUXO COMPLETO

```
1. Extrair ZIP
   ↓
2. Criar banco no pgAdmin
   ↓
3. Configurar application.properties
   ↓
4. mvn spring-boot:run
   ↓
5. Run MainApp.java
   ↓
6. Usar o sistema! 🎉
```

---

## ❌ ERROS COMUNS

### Erro: "Connection refused"
**Causa:** PostgreSQL não está rodando  
**Solução:** Inicie o serviço PostgreSQL

### Erro: "database does not exist"
**Causa:** Esqueceu de criar o banco  
**Solução:** Crie o banco `pdvpostocombustivel` no pgAdmin

### Erro: "Port 8080 already in use"
**Causa:** Backend já está rodando  
**Solução:** Pare o processo anterior

### Erro: "password authentication failed"
**Causa:** Senha errada no application.properties  
**Solução:** Corrija a senha

---

## 📊 CHECKLIST DE INSTALAÇÃO

- [ ] Java 17+ instalado
- [ ] Maven instalado
- [ ] PostgreSQL instalado e rodando
- [ ] ZIP extraído
- [ ] Banco `pdvpostocombustivel` criado
- [ ] `application.properties` configurado com senha
- [ ] Backend iniciou sem erros
- [ ] Tabelas criadas automaticamente
- [ ] Frontend abre a janela
- [ ] Sistema funcionando

**Todos marcados?** ✅ **Instalação completa!**

---

## 🎊 RESUMO

**Para instalar em um PC novo:**

1. ✅ Crie o banco: `pdvpostocombustivel`
2. ✅ Configure a senha no `application.properties`
3. ✅ Execute: `mvn spring-boot:run`
4. ✅ Execute: `MainApp.java`
5. ✅ Pronto! Use o sistema!

**É simples assim!** 🚀

---

## 📝 NOTAS IMPORTANTES

⚠️ **O banco deve ser criado ANTES de executar o backend**

⚠️ **As tabelas são criadas automaticamente pelo Hibernate**

⚠️ **Você NÃO precisa executar scripts SQL manualmente**

⚠️ **O arquivo `.example` é só um modelo, não edite ele**

✅ **Sempre use `application.properties` (sem .example)**

---

**Qualquer dúvida, consulte:** `COMO_CONFIGURAR_E_TESTAR.md`

