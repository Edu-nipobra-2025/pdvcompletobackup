# 📚 Documentação da API - PDV Posto Combustível

## 🌐 URL Base
```
http://localhost:8080
```

## 📖 Swagger UI
Acesse a documentação interativa em: **http://localhost:8080/swagger-ui.html**

---

## 🔌 Endpoints Principais

### 1. **Produtos** (`/api/produtos`)

#### Criar Produto
```http
POST /api/produtos
Content-Type: application/json

{
  "nome": "Gasolina Aditivada",
  "referencia": "GAS-ADT-001",
  "fornecedor": "Petrobras",
  "categoria": "Combustível",
  "marca": "Podium"
}
```

**Resposta (201 Created):**
```json
{
  "id": 1,
  "nome": "Gasolina Aditivada",
  "referencia": "GAS-ADT-001",
  "fornecedor": "Petrobras",
  "categoria": "Combustível",
  "marca": "Podium"
}
```

#### Listar Todos os Produtos
```http
GET /api/produtos
```

#### Buscar por ID
```http
GET /api/produtos/1
```

#### Atualizar Produto
```http
PUT /api/produtos/1
Content-Type: application/json

{
  "nome": "Gasolina Premium",
  "referencia": "GAS-PREM-001",
  "fornecedor": "Shell",
  "categoria": "Combustível",
  "marca": "V-Power"
}
```

#### Deletar Produto
```http
DELETE /api/produtos/1
```

---

### 2. **Estoque** (`/api/estoque`)

#### Registrar Entrada de Estoque
```http
POST /api/estoque
Content-Type: application/json

{
  "produtoId": 1,
  "quantidade": 1000,
  "unidade": "LITROS"
}
```

#### Consultar Estoque
```http
GET /api/estoque/produto/1
```

---

### 3. **Preços** (`/api/precos`)

#### Definir Preço
```http
POST /api/precos
Content-Type: application/json

{
  "produtoId": 1,
  "valor": 5.89,
  "dataInicio": "2025-10-17",
  "dataFim": null
}
```

#### Consultar Preço Atual
```http
GET /api/precos/produto/1/atual
```

---

### 4. **Pessoas** (`/api/pessoas`)

#### Cadastrar Cliente/Funcionário
```http
POST /api/pessoas
Content-Type: application/json

{
  "nome": "João Silva",
  "cpf": "123.456.789-00",
  "tipo": "CLIENTE",
  "telefone": "(11) 98765-4321",
  "email": "joao@email.com"
}
```

---

## 🚨 Códigos de Resposta

| Código | Significado | Quando ocorre |
|--------|-------------|---------------|
| 200 | OK | Operação bem-sucedida |
| 201 | Created | Recurso criado com sucesso |
| 400 | Bad Request | Dados inválidos enviados |
| 404 | Not Found | Recurso não encontrado |
| 500 | Server Error | Erro interno do servidor |

---

## 🛡️ Exemplos de Erros (Com as melhorias aplicadas)

### Erro de Validação (400)
```json
{
  "status": 400,
  "message": "Erro de validação",
  "timestamp": "2025-10-17T03:30:00",
  "errors": {
    "nome": "Nome é obrigatório",
    "referencia": "Referência deve ter no mínimo 3 caracteres"
  }
}
```

### Recurso Não Encontrado (404)
```json
{
  "status": 404,
  "message": "Produto não encontrado",
  "timestamp": "2025-10-17T03:30:00"
}
```

---

## 🧪 Como Testar

### Usando cURL
```bash
# Criar produto
curl -X POST http://localhost:8080/api/produtos \
  -H "Content-Type: application/json" \
  -d '{"nome":"Diesel S10","referencia":"DIS-S10","fornecedor":"Petrobras","categoria":"Combustível","marca":"BR"}'

# Listar produtos
curl http://localhost:8080/api/produtos
```

### Usando Postman
1. Importe a coleção do Swagger
2. Configure a URL base: `http://localhost:8080`
3. Execute as requisições

### Usando o Swagger UI (Recomendado)
1. Acesse http://localhost:8080/swagger-ui.html
2. Escolha o endpoint
3. Clique em "Try it out"
4. Preencha os dados
5. Clique em "Execute"

---

## 📊 Fluxo Completo de Uso

### Cenário: Registrar Venda de Combustível

1. **Cadastrar o Produto**
```http
POST /api/produtos
{
  "nome": "Gasolina Comum",
  "referencia": "GAS-COM",
  "fornecedor": "Petrobras",
  "categoria": "Combustível",
  "marca": "BR"
}
```

2. **Adicionar ao Estoque**
```http
POST /api/estoque
{
  "produtoId": 1,
  "quantidade": 10000,
  "unidade": "LITROS"
}
```

3. **Definir Preço**
```http
POST /api/precos
{
  "produtoId": 1,
  "valor": 5.49,
  "dataInicio": "2025-10-17"
}
```

4. **Cadastrar Cliente**
```http
POST /api/pessoas
{
  "nome": "Maria Santos",
  "cpf": "987.654.321-00",
  "tipo": "CLIENTE",
  "telefone": "(11) 91234-5678"
}
```

---

## 🔧 Configurações Úteis

### CORS (Já configurado)
A API aceita requisições de:
- `http://localhost:8080`
- `http://localhost:3000`

### Paginação (Futuro)
Quando implementado, use:
```http
GET /api/produtos?page=0&size=10&sort=nome,asc
```

---

**Última atualização:** 2025-10-17  
**Versão da API:** 1.0

