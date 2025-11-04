# 🏪 PDV Posto de Combustível

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)
![Swing](https://img.shields.io/badge/GUI-Java%20Swing-red)
![License](https://img.shields.io/badge/license-MIT-green)

Sistema de Ponto de Venda (PDV) para Posto de Combustível desenvolvido em **Java** com **Spring Boot** (Backend) e **Java Swing** (Frontend).

---

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Funcionalidades](#-funcionalidades)
- [Tecnologias](#-tecnologias)
- [Arquitetura](#-arquitetura)
- [Pré-requisitos](#-pré-requisitos)
- [Instalação](#-instalação)
- [Como Usar](#-como-usar)
- [API REST](#-api-rest)
- [Screenshots](#-screenshots)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Contribuindo](#-contribuindo)
- [Licença](#-licença)
- [Contato](#-contato)

---

## 🎯 Sobre o Projeto

O **PDV Posto de Combustível** é um sistema completo de gerenciamento para postos de combustível, desenvolvido como projeto acadêmico utilizando boas práticas de programação orientada a objetos, arquitetura REST e padrões de projeto.

O sistema oferece uma interface gráfica moderna desenvolvida em **Java Swing** que se comunica com uma **API REST** desenvolvida em **Spring Boot**, com persistência de dados em **PostgreSQL**.

### ✨ Destaques

- ✅ **Sistema de Login e Cadastro** com autenticação
- ✅ **CRUD Completo** para 7 entidades diferentes
- ✅ **Interface Gráfica Moderna** com Java Swing
- ✅ **API REST** documentada com Swagger/OpenAPI
- ✅ **Banco de Dados** PostgreSQL com JPA/Hibernate
- ✅ **Registro de Acessos** automático
- ✅ **Validações** de dados (CPF, CNPJ, campos obrigatórios)
- ✅ **Tratamento de Exceções** global

---

## 🚀 Funcionalidades

### 🔐 Autenticação
- **Login** - Sistema de autenticação de usuários
- **Cadastro** - Registro de novos usuários
- **Sessão** - Gerenciamento de sessão local
- **Registro de Acessos** - Histórico de logins no banco de dados

### 📊 Gerenciadores (CRUD Completo)

1. **👥 Pessoas**
   - Cadastro de clientes e funcionários
   - Suporte para Pessoa Física (CPF) e Jurídica (CNPJ)
   - Validação de CPF/CNPJ

2. **📦 Produtos**
   - Gerenciamento de produtos do posto
   - Descrição, código de barras, etc.

3. **💰 Preços**
   - Controle de preços dos produtos
   - Histórico de alterações

4. **💵 Custos**
   - Registro de custos operacionais
   - Controle de despesas

5. **📦 Estoques**
   - Controle de estoque de produtos
   - Quantidade disponível
   - Movimentações

6. **🔑 Acessos**
   - Gerenciamento de usuários do sistema
   - Visualização de histórico de logins

7. **📞 Contatos**
   - Cadastro de contatos
   - Telefones, emails, etc.

---

## 🛠️ Tecnologias

### Backend
- **Java 17**
- **Spring Boot 3.2.5**
  - Spring Web (REST API)
  - Spring Data JPA
  - Spring Validation
  - Spring DevTools
- **PostgreSQL 15**
- **Hibernate** (JPA Implementation)
- **Swagger/OpenAPI 2.3.0** (Documentação da API)
- **Maven** (Gerenciamento de dependências)

### Frontend
- **Java 17**
- **Java Swing** (Interface Gráfica)
- **Jackson** (Serialização JSON)
- **Maven**

### Banco de Dados
- **PostgreSQL 15**
- **7 Tabelas**:
  - `pessoas`
  - `produtos`
  - `precos`
  - `custos`
  - `estoques`
  - `acessos`
  - `contatos`

---

## 🏗️ Arquitetura

```
┌─────────────────────────────────────────────────────────────┐
│                    FRONTEND (Java Swing)                    │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐   │
│  │  Views   │  │ Services │  │   DTOs   │  │  Models  │   │
│  └──────────┘  └──────────┘  └──────────┘  └──────────┘   │
└─────────────────────────────────────────────────────────────┘
                            ↕ HTTP/JSON
┌─────────────────────────────────────────────────────────────┐
│                  BACKEND (Spring Boot REST API)             │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐   │
│  │Controller│→ │ Service  │→ │Repository│→ │ Entity   │   │
│  └──────────┘  └──────────┘  └──────────┘  └──────────┘   │
└─────────────────────────────────────────────────────────────┘
                            ↕ JPA/Hibernate
┌─────────────────────────────────────────────────────────────┐
│                   DATABASE (PostgreSQL)                     │
│              7 Tabelas com Relacionamentos                  │
└─────────────────────────────────────────────────────────────┘
```

### Padrões de Projeto Utilizados
- **MVC** (Model-View-Controller)
- **DTO** (Data Transfer Object)
- **Repository Pattern**
- **Service Layer Pattern**
- **Dependency Injection**
- **RESTful API**

---

## 📦 Pré-requisitos

Antes de começar, você precisa ter instalado:

- **Java JDK 17+** - [Download](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.8+** - [Download](https://maven.apache.org/download.cgi)
- **PostgreSQL 15+** - [Download](https://www.postgresql.org/download/)
- **IDE** (IntelliJ IDEA recomendado) - [Download](https://www.jetbrains.com/idea/download/)

Verificar instalações:
```bash
java -version
mvn -version
psql --version
```

---

## ⚙️ Instalação

### 1️⃣ Clonar o Repositório

```bash
git clone https://github.com/Sidney-Emanuel-Oliveira/pdv-posto-combustivel.git
cd pdv-posto-combustivel
```

### 2️⃣ Criar Banco de Dados

**Opção 1: Via pgAdmin**
1. Abra o pgAdmin 4
2. Conecte ao servidor PostgreSQL
3. Clique com botão direito em "Databases"
4. Selecione "Create" → "Database"
5. Nome: `pdvpostocombustivel`
6. Salvar

**Opção 2: Via SQL**
```sql
CREATE DATABASE pdvpostocombustivel;
```

### 3️⃣ Configurar Senha do PostgreSQL

Navegue até:
```
pdv-posto-combustivel/src/main/resources/
```

**Se não existir `application.properties`, copie de:**
```
application.properties.example → application.properties
```

**Edite a senha:**
```properties
spring.datasource.password=SUA_SENHA_AQUI
```

### 4️⃣ Executar o Backend

**Via Maven:**
```bash
cd pdv-posto-combustivel
mvn spring-boot:run
```

**Via IntelliJ:**
1. Abra: `PdvpostocombustivelApplication.java`
2. Clique com botão direito → Run

✅ **Backend rodando em:** `http://localhost:8080`

### 5️⃣ Executar o Frontend

**Via IntelliJ:**
1. Abra: `JavaPoo-Front-End-main`
2. Navegue: `src/main/java/br/com/PdvFrontEnd/view/MainApp.java`
3. Clique com botão direito → Run 'MainApp.main()'

✅ **Interface gráfica abrirá automaticamente!**

---

## 💻 Como Usar

### Primeiro Acesso

1. Execute o **Backend**
2. Execute o **Frontend**
3. Sistema abrirá tela de **CADASTRO** (primeira vez)
4. Preencha seus dados e clique em "Cadastrar"
5. Faça **LOGIN** com suas credenciais
6. Tela de **GERENCIAMENTO** será exibida

### Próximos Acessos

1. Execute o **Backend**
2. Execute o **Frontend**
3. Sistema abrirá tela de **LOGIN**
4. Digite usuário e senha
5. Acesse os gerenciadores

### Gerenciadores Disponíveis

- 👥 **Gerenciar Pessoas** - CRUD de clientes/funcionários
- 💰 **Gerenciar Preços** - Controle de preços
- 📦 **Gerenciar Produtos** - Cadastro de produtos
- 💵 **Gerenciar Custos** - Registro de custos
- 📦 **Gerenciar Estoques** - Controle de estoque
- 🔑 **Gerenciar Acessos** - Usuários e histórico
- 📞 **Gerenciar Contatos** - Cadastro de contatos

---

## 📡 API REST

### Swagger/OpenAPI

Acesse a documentação interativa da API:

```
http://localhost:8080/swagger-ui.html
```

### Endpoints Principais

#### 👥 Pessoas
```http
GET    /api/v1/pessoas          - Listar todas
GET    /api/v1/pessoas/{id}     - Buscar por ID
POST   /api/v1/pessoas          - Criar nova
PUT    /api/v1/pessoas/{id}     - Atualizar
DELETE /api/v1/pessoas/{id}     - Remover
```

#### 📦 Produtos
```http
GET    /api/produtos            - Listar todos
GET    /api/produtos/{id}       - Buscar por ID
POST   /api/produtos            - Criar novo
PUT    /api/produtos/{id}       - Atualizar
DELETE /api/produtos/{id}       - Remover
```

#### 💰 Preços, 💵 Custos, 📦 Estoques, 🔑 Acessos, 📞 Contatos
Seguem o mesmo padrão REST acima.

### Exemplo de Requisição

**Criar Pessoa:**
```json
POST /api/v1/pessoas
Content-Type: application/json

{
  "nomeCompleto": "João Silva",
  "cpfCnpj": "12345678901",
  "dataNascimento": "1990-01-01",
  "tipoPessoa": "FISICA",
  "role": "CLIENTE"
}
```

---

## 📸 Screenshots

### Tela de Login
```
╔═══════════════════════════════════╗
║         🔐 LOGIN                  ║
╠═══════════════════════════════════╣
║  Usuário: [__________________]    ║
║  Senha:   [__________________]    ║
║                                   ║
║     [     ENTRAR     ]            ║
║     [    CADASTRAR   ]            ║
╚═══════════════════════════════════╝
```

### Tela Principal
```
╔═══════════════════════════════════╗
║   🏪 GERENCIAMENTO PDV            ║
╠═══════════════════════════════════╣
║  [ Gerenciar Pessoas      ]       ║
║  [ Gerenciar Preços       ]       ║
║  [ Gerenciar Produtos     ]       ║
║  [ Gerenciar Custos       ]       ║
║  [ Gerenciar Estoques     ]       ║
║  [ Gerenciar Acessos      ]       ║
║  [ Gerenciar Contatos     ]       ║
╚═══════════════════════════════════╝
```

---

## 📁 Estrutura do Projeto

```
pdv-posto-combustivel/
├── pdv-posto-combustivel/          # Backend (Spring Boot)
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/br/pdvpostocombustivel/
│   │   │   │       ├── api/             # Controllers REST
│   │   │   │       │   ├── pessoa/
│   │   │   │       │   ├── produto/
│   │   │   │       │   ├── preco/
│   │   │   │       │   ├── custo/
│   │   │   │       │   ├── estoque/
│   │   │   │       │   ├── acesso/
│   │   │   │       │   └── contato/
│   │   │   │       ├── config/          # Configurações
│   │   │   │       ├── domain/
│   │   │   │       │   ├── entity/      # Entidades JPA
│   │   │   │       │   └── repository/  # Repositories
│   │   │   │       ├── enums/           # Enumerações
│   │   │   │       ├── exception/       # Exceções customizadas
│   │   │   │       └── util/            # Utilitários
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       └── schema.sql
│   │   └── test/                        # Testes
│   └── pom.xml
│
├── JavaPoo-Front-End-main/             # Frontend (Java Swing)
│   ├── src/
│   │   └── main/
│   │       └── java/
│   │           └── br/com/PdvFrontEnd/
│   │               ├── view/            # Telas Swing
│   │               │   ├── MainApp.java
│   │               │   ├── LoginView.java
│   │               │   ├── RegisterView.java
│   │               │   ├── PessoaList.java
│   │               │   ├── PessoaForm.java
│   │               │   └── ...
│   │               ├── service/         # Services HTTP
│   │               ├── model/           # Models
│   │               ├── dto/             # DTOs
│   │               └── util/            # Utilitários
│   └── pom.xml
│
├── _docs_e_scripts/                    # Documentação e Scripts SQL
│   ├── PASSO1_CRIAR_BANCO.sql
│   ├── PASSO2_CRIAR_TABELAS_E_DADOS.sql
│   ├── GUIA_BANCO_DE_DADOS.md
│   └── ...
│
├── COMO_CONFIGURAR_E_TESTAR.md         # Guia de instalação
├── README.md                            # Este arquivo
└── pom.xml
```

---

## 🤝 Contribuindo

Contribuições são bem-vindas! Para contribuir:

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

---

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 👨‍💻 Autor

**Sidney Emanuel Oliveira**

- GitHub: [@Sidney-Emanuel-Oliveira](https://github.com/Sidney-Emanuel-Oliveira)
- LinkedIn: [Sidney Emanuel](https://linkedin.com/in/sidney-emanuel)

---

## 📞 Contato

Para dúvidas, sugestões ou reportar problemas:

- 📧 Email: sidney.emanuel@example.com
- 🐛 Issues: [GitHub Issues](https://github.com/Sidney-Emanuel-Oliveira/pdv-posto-combustivel/issues)

---

## 🎓 Projeto Acadêmico

Este projeto foi desenvolvido como trabalho acadêmico para demonstrar conhecimentos em:

- ✅ Programação Orientada a Objetos (POO)
- ✅ Desenvolvimento de APIs REST
- ✅ Persistência de dados com JPA/Hibernate
- ✅ Desenvolvimento de interfaces gráficas
- ✅ Integração Backend-Frontend
- ✅ Padrões de Projeto
- ✅ Boas Práticas de Programação

---

## 🔗 Links Úteis

- [Documentação Spring Boot](https://spring.io/projects/spring-boot)
- [Documentação PostgreSQL](https://www.postgresql.org/docs/)
- [Documentação Java Swing](https://docs.oracle.com/javase/tutorial/uiswing/)
- [Guia de Instalação Completo](COMO_CONFIGURAR_E_TESTAR.md)

---

<div align="center">

**⭐ Se este projeto te ajudou, considere dar uma estrela! ⭐**

Desenvolvido com ❤️ por [Sidney Emanuel Oliveira](https://github.com/Sidney-Emanuel-Oliveira)

</div>

