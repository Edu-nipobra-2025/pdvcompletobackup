# 🛠️ Guia Completo: Como Configurar o Projeto em Outro Computador

## 📥 Passo a Passo - Do Zero ao Funcionamento

### ✅ Pré-requisitos Instalados

Antes de começar, certifique-se de ter instalado:

1. **Java JDK 17** - https://www.oracle.com/java/technologies/downloads/#java17
2. **Maven 3.8+** - https://maven.apache.org/download.cgi
3. **PostgreSQL 16+** - https://www.postgresql.org/download/
4. **IntelliJ IDEA** - https://www.jetbrains.com/idea/download/
5. **Git** - https://git-scm.com/downloads

---

## 🚀 Passo 1: Baixar o Projeto do GitHub

### Opção A: Clonar com Git
```bash
git clone https://github.com/Sidney-Emanuel-Oliveira/pdv-posto-combustivel.git
cd pdv-posto-combustivel
```

### Opção B: Download ZIP
1. Acesse: https://github.com/Sidney-Emanuel-Oliveira/pdv-posto-combustivel
2. Clique em **"Code"** → **"Download ZIP"**
3. Extraia o arquivo ZIP em uma pasta de sua escolha
4. Abra o terminal na pasta extraída

---

## 🔧 Passo 2: Configurar no IntelliJ IDEA

### ⚠️ IMPORTANTE: Abra SEMPRE pela pasta RAIZ!

1. **Abra o IntelliJ IDEA**

2. **File → Open**

3. **Navegue até a pasta raiz `pdvpostocombustivel`**
   - ✅ A pasta que contém o arquivo `pom.xml` principal
   - ✅ Deve conter as subpastas: `pdv-posto-combustivel/` e `JavaPoo-Front-End-main/`

4. **Clique em "OK" ou "Open"**

5. **O IntelliJ vai perguntar "Trust and Open Project Maven"**
   - ✅ Clique em **"Trust Project"**

6. **Aguarde o IntelliJ detectar o projeto Maven multi-módulo**
   - 📦 Você verá no canto inferior direito: "Importing Maven Projects..."
   - ⏱️ Isso pode levar de 2 a 10 minutos (depende da internet)
   - ✅ Todas as dependências (Swagger, Jakarta, Spring, etc.) serão baixadas automaticamente

### 🎯 Como Verificar se Está Correto

**No painel do Maven (lateral direita):**
```
📦 pdv-posto-combustivel-parent
  ├── 📦 pdv-posto-combustivel (Backend)
  │   └── Lifecycle, Plugins, Dependencies
  └── 📦 JavaPoo-Front-End-main (Frontend)
      └── Lifecycle, Plugins, Dependencies
```

**As pastas `src/main/java` devem estar:**
- 🔵 **Azuis** (marcadas como Source Root)
- ❌ **NÃO laranjas** ou vermelhas

**Os imports não devem estar vermelhos**

---

## 🔍 Passo 3: Resolver Problemas Comuns

### ❌ Problema: Pastas laranjas ou vermelhas

**Solução Automática:**
1. Clique com botão direito no `pom.xml` da **raiz**
2. Selecione **"Add as Maven Project"** ou **"Reload Project"**
3. Aguarde o IntelliJ reindexar

**Solução Manual (se necessário):**
1. `File` → `Project Structure` (Ctrl+Alt+Shift+S)
2. Vá em **"Modules"**
3. Clique em **"+"** → **"Import Module"**
4. Selecione `pdv-posto-combustivel/pom.xml`
5. Repita para `JavaPoo-Front-End-main/pom.xml`

---

### ❌ Problema: Imports em vermelho

**Solução:**
1. Clique com botão direito na pasta raiz do projeto
2. **"Maven"** → **"Reload Project"**
3. Ou clique no ícone de reload no painel Maven (🔄)

---

### ❌ Problema: "Cannot resolve symbol" em annotations como @Entity, @RestController

**Solução:**
1. Abra o terminal no IntelliJ (Alt+F12)
2. Execute:
```bash
mvn clean install -U
```
3. Aguarde o Maven baixar todas as dependências
4. `File` → `Invalidate Caches` → `Invalidate and Restart`

---

## 🗄️ Passo 4: Configurar o Banco de Dados PostgreSQL

### 1️⃣ Criar o Banco de Dados

Abra o **pgAdmin 4** ou **psql** e execute:

```sql
CREATE DATABASE pdvpostocombustivel;
```

### 2️⃣ Executar os Scripts de Inicialização

**Opção A: Usando pgAdmin**
1. Conecte-se ao banco `pdvpostocombustivel`
2. Abra a Query Tool (ícone de raio)
3. Abra o arquivo: `_docs_e_scripts/PASSO1_CRIAR_BANCO.sql`
4. Clique em **Execute** (F5)
5. Repita para: `_docs_e_scripts/PASSO2_CRIAR_TABELAS_E_DADOS.sql`

**Opção B: Linha de comando (psql)**
```bash
psql -U postgres
\c pdvpostocombustivel
\i _docs_e_scripts/PASSO1_CRIAR_BANCO.sql
\i _docs_e_scripts/PASSO2_CRIAR_TABELAS_E_DADOS.sql
```

### 3️⃣ Configurar a Senha no Backend

1. Navegue até: `pdv-posto-combustivel/src/main/resources/`
2. **Copie** o arquivo `application.properties.example`
3. **Cole** e renomeie para `application.properties`
4. **Edite** o novo arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/pdvpostocombustivel
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA_DO_POSTGRESQL_AQUI  # ← ALTERE AQUI!
```

**⚠️ IMPORTANTE:** 
- O arquivo `application.properties` já está no `.gitignore`
- **NUNCA** faça commit deste arquivo com sua senha real
- Use sempre o `application.properties.example` como template

---

## ▶️ Passo 5: Executar o Projeto

### 🎯 Método 1: Pelo IntelliJ (Mais Fácil)

#### **Executar o Backend:**
1. No canto superior direito, procure por configurações de execução
2. Se não existir, crie uma nova:
   - Clique no dropdown → **"Edit Configurations..."**
   - Clique em **"+"** → **"Application"**
   - **Name:** Backend - PDV Posto Combustivel
   - **Main class:** `src.main.java.com.br.pdvpostocombustivel.PdvpostocombustivelApplication`
   - **Module:** `pdvpostocombustivel`
   - Clique em **"Apply"** → **"OK"**
3. Selecione a configuração **"Backend - PDV Posto Combustivel"**
4. Clique no botão verde ▶️ **Run**
5. Aguarde aparecer no console: `Started PdvpostocombustivelApplication`
6. ✅ Backend funcionando em: http://localhost:8080
7. ✅ Swagger disponível em: http://localhost:8080/swagger-ui.html

#### **Executar o Frontend:**
1. Crie outra configuração:
   - **Name:** Frontend - MainApp
   - **Main class:** `br.com.PdvFrontEnd.view.MainApp`
   - **Module:** `pdv-frontend`
2. Selecione **"Frontend - MainApp"**
3. Clique em ▶️ **Run**
4. A interface gráfica Swing abrirá automaticamente

---

### 🎯 Método 2: Linha de Comando

**Terminal 1 - Backend:**
```bash
cd pdv-posto-combustivel
mvn clean install
mvn spring-boot:run
```

**Terminal 2 - Frontend:**
```bash
cd JavaPoo-Front-End-main
mvn clean install
mvn exec:java -Dexec.mainClass="br.com.PdvFrontEnd.view.MainApp"
```

---

### 🎯 Método 3: Scripts .bat (Windows)

**Backend:**
```bash
_docs_e_scripts\EXECUTAR_BACKEND.bat
```

**Frontend:**
```bash
_docs_e_scripts\EXECUTAR_FRONTEND.bat
```

---

## ✅ Verificar se Está Funcionando

### 1. **Backend (API REST)**
- Acesse: http://localhost:8080/swagger-ui.html
- Você deve ver a interface do Swagger com todos os endpoints

### 2. **Frontend (GUI Swing)**
- A janela do sistema PDV deve abrir
- Teste criar um produto, cliente, etc.

### 3. **Banco de Dados**
- Abra o pgAdmin
- Navegue: `Databases` → `pdvpostocombustivel` → `Schemas` → `public` → `Tables`
- Você deve ver todas as tabelas criadas

---

## 📝 Checklist Final

Antes de começar a usar o sistema, verifique:

- [ ] Java 17 instalado e configurado
- [ ] Maven 3.8+ instalado
- [ ] PostgreSQL rodando
- [ ] Banco `pdvpostocombustivel` criado
- [ ] Scripts SQL executados
- [ ] Arquivo `application.properties` configurado com sua senha
- [ ] IntelliJ reconheceu os 2 módulos Maven
- [ ] Todas as dependências foram baixadas
- [ ] Backend rodando na porta 8080
- [ ] Swagger acessível em http://localhost:8080/swagger-ui.html
- [ ] Frontend Swing abre corretamente

---

## 🆘 Precisa de Ajuda?

Consulte os outros guias em `_docs_e_scripts/`:
- `COMO_CONFIGURAR_E_TESTAR.md` - Guia de testes
- `COMO_EXECUTAR_PELO_MAIN.md` - Executar pela classe Main
- `RESOLVER_ERRO_404.txt` - Resolver erros 404
- `LIBERAR_PORTA_8080.bat` - Liberar porta 8080

---

## 🎓 Para o Professor/Avaliador

Este projeto está 100% funcional e pronto para ser avaliado:

1. ✅ **Clone o repositório** do GitHub
2. ✅ **Abra no IntelliJ** pela pasta raiz `pdvpostocombustivel`
3. ✅ **Configure** o arquivo `application.properties` com sua senha do PostgreSQL
4. ✅ **Execute** os scripts SQL no banco de dados
5. ✅ **Execute** o Backend e Frontend
6. ✅ **Teste** todas as funcionalidades pelo Swagger ou pela interface Swing

**Tempo estimado:** 15-20 minutos (incluindo download das dependências)

---

Desenvolvido por **Sidney Emanuel Oliveira**
GitHub: https://github.com/Sidney-Emanuel-Oliveira

