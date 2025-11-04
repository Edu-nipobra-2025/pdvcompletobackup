# 🔧 Guia de Teste - Integração Front-End com Back-End

## ✅ Correções Aplicadas:

### Problema 1: Erro ao Listar Pessoas (RESOLVIDO)
**Erro anterior:**
```
Cannot deserialize value of type br.com.PdvFrontEnd.dto.PessoaResponse[] from Object value
```

**Causa:** A API retorna uma resposta paginada, não um array simples.

**Solução:** 
- Criado `PageResponse.java` para mapear respostas paginadas
- Ajustado `PessoaService.listPessoas()` para fazer parse correto

### Problema 2: Erro ao Adicionar Pessoa (RESOLVIDO)
**Causa:** Tipo de pessoa não estava em uppercase (back-end espera enum)

**Solução:**
- Adicionado `.toUpperCase()` ao tipo da pessoa
- Melhorado tratamento de erros com `e.printStackTrace()`

---

## 📋 Passo a Passo para Testar:

### 1️⃣ Iniciar o Back-End
```bash
cd "C:\Users\sidney\Documents\faculdades 2025\pdv-posto-combustivel-main\pdvpostocombustivel\pdv-posto-combustivel"
mvnw spring-boot:run
```

✅ **Verificar:** Acesse http://localhost:8080/swagger-ui.html
- Você deve ver a documentação da API

### 2️⃣ Verificar o PostgreSQL
- ✅ PostgreSQL rodando na porta 5432
- ✅ Database: `pdvpostocombustivel`
- ✅ User: `postgres`
- ✅ Password: `Sidney123@`

### 3️⃣ Compilar o Front-End
```bash
cd "C:\Users\sidney\Documents\faculdades 2025\pdv-posto-combustivel-main\pdvpostocombustivel\JavaPoo-Front-End-main"
mvn clean package
```

### 4️⃣ Executar o Front-End
```bash
mvn spring-boot:run
```

---

## 🧪 Testes a Realizar:

### Teste 1: Listar Pessoas
1. Clique em "Gerenciar Pessoas" no menu
2. A tela deve abrir sem erros
3. Se houver pessoas no banco, elas devem aparecer na tabela

**Resultado Esperado:** ✅ Tela abre e lista pessoas (ou mostra tabela vazia)

### Teste 2: Adicionar Pessoa
1. Clique no botão "Adicionar"
2. Preencha os campos:
   - **Nome:** João Silva
   - **CPF:** 12345678900
   - **Data de Nascimento:** 2000-01-15
   - **Tipo:** FISICA (ou JURIDICA)
   - **Função:** USER
3. Clique em OK

**Resultado Esperado:** 
- ✅ Mensagem "Pessoa adicionada com sucesso!"
- ✅ Pessoa aparece na tabela após clicar em "Atualizar"
- ✅ Pessoa está salva no banco de dados

### Teste 3: Verificar no Banco
Abra o PostgreSQL e execute:
```sql
SELECT * FROM pessoas;
```

**Resultado Esperado:** ✅ A pessoa adicionada deve aparecer no banco

### Teste 4: Remover Pessoa
1. Selecione uma linha na tabela
2. Clique em "Remover"
3. Confirme a ação

**Resultado Esperado:** 
- ✅ Mensagem "Pessoa removida com sucesso!"
- ✅ Pessoa desaparece da tabela
- ✅ Pessoa foi deletada do banco de dados

---

## ⚠️ Se der erro:

### Erro: "Connection refused" ou "404 Not Found"
**Solução:** Certifique-se de que o back-end está rodando em http://localhost:8080

Teste no navegador:
```
http://localhost:8080/api/v1/pessoas
```

### Erro: "tipoPessoa" inválido
**Solução:** Use apenas:
- `FISICA` (para pessoa física)
- `JURIDICA` (para pessoa jurídica)

Os valores devem estar em MAIÚSCULAS.

### Erro: Data inválida
**Solução:** Use o formato exato:
```
YYYY-MM-DD
Exemplo: 2000-01-15
```

### Ver erros detalhados
Se ocorrer algum erro, olhe o console onde o front-end está rodando. 
Os erros completos serão exibidos com `e.printStackTrace()`.

---

## 📊 Estrutura dos Dados:

### Campos Obrigatórios para Pessoa:
- ✅ **nomeCompleto** (String) - Nome da pessoa
- ✅ **cpfCnpj** (String) - CPF (11 dígitos) ou CNPJ (14 dígitos)
- ✅ **dataNascimento** (LocalDate) - Formato: YYYY-MM-DD
- ✅ **tipoPessoa** (Enum) - FISICA ou JURIDICA

### Campos Opcionais:
- **numeroCtps** (Long) - Número da carteira de trabalho

---

## 🎯 Endpoints Utilizados:

| Ação | Método | Endpoint | Descrição |
|------|--------|----------|-----------|
| Listar | GET | `/api/v1/pessoas?size=1000` | Lista todas as pessoas (paginado) |
| Adicionar | POST | `/api/v1/pessoas` | Cria nova pessoa |
| Atualizar | PUT | `/api/v1/pessoas/{id}` | Atualiza pessoa existente |
| Deletar | DELETE | `/api/v1/pessoas/{id}` | Remove pessoa |

---

## ✨ Melhorias Aplicadas:

1. ✅ Suporte a respostas paginadas da API
2. ✅ Tratamento correto de enums (TipoPessoa)
3. ✅ Conversão automática de datas (String ↔ LocalDate)
4. ✅ Mensagens de erro detalhadas para debug
5. ✅ Validação de campos obrigatórios
6. ✅ Uso correto de IDs para deletar registros

---

## 🐛 Debug:

Se ainda houver erros, verifique no console do front-end a stack trace completa.
O erro será impresso com todos os detalhes graças ao `e.printStackTrace()`.

**Exemplos de erros comuns:**
- `400 Bad Request` → Campos inválidos ou faltando
- `404 Not Found` → Back-end não está rodando
- `500 Internal Server Error` → Erro no back-end (verifique logs do Spring Boot)

---

**Desenvolvido para PDV Posto de Combustível - 2025**
**Integração Front-End (Swing) ↔ Back-End (Spring Boot) ↔ PostgreSQL**

