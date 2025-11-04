# Integração Front-End e Back-End - PDV Posto de Combustível

## 🎉 Integração Completa Realizada!

Seu front-end Swing agora está **totalmente integrado** com o back-end Spring Boot e o banco de dados PostgreSQL!

## 📋 O que foi feito:

### 1. **Cliente HTTP REST**
- Criado `HttpClient.java` para comunicação com a API
- Suporta operações: GET, POST, PUT, DELETE
- Serialização/deserialização automática com Jackson

### 2. **DTOs Criados**
Foram criados DTOs para todas as entidades:
- ✅ **PessoaRequest/Response** - Para gerenciar pessoas
- ✅ **ProdutoRequest/Response** - Para gerenciar produtos
- ✅ **PrecoRequest/Response** - Para gerenciar preços
- ✅ **AcessoRequest/Response** - Para gerenciar acessos
- ✅ **ContatoRequest/Response** - Para gerenciar contatos
- ✅ **CustoRequest/Response** - Para gerenciar custos
- ✅ **EstoqueRequest/Response** - Para gerenciar estoque

### 3. **Services Atualizados**
Todos os services agora consomem a API REST:
- ✅ **PessoaService** - Integrado com `/api/v1/pessoas`
- ✅ **ProdutoService** - Integrado com `/api/produtos`
- ✅ **PrecoService** - Integrado com `/api/precos`
- ✅ **AcessoService** - Integrado com `/api/acessos`
- ✅ **ContatoService** - Integrado com `/api/contatos`
- ✅ **CustoService** - Integrado com `/api/custos`
- ✅ **EstoqueService** - Integrado com `/api/estoques`

## 🚀 Como Usar:

### Passo 1: Iniciar o Back-End
```bash
cd pdv-posto-combustivel
mvnw spring-boot:run
```

O back-end estará disponível em: `http://localhost:8080`
Swagger UI: `http://localhost:8080/swagger-ui.html`

### Passo 2: Verificar o Banco de Dados
Certifique-se de que o PostgreSQL está rodando com as configurações:
- **Host:** localhost:5432
- **Database:** pdvpostocombustivel
- **User:** postgres
- **Password:** Sidney123@

### Passo 3: Compilar o Front-End
```bash
cd JavaPoo-Front-End-main
mvn clean install
```

### Passo 4: Executar o Front-End
```bash
mvn spring-boot:run
```

Ou execute a classe principal: `br.com.PdvFrontEnd.view.MainApp`

## 📊 Funcionalidades Disponíveis:

### No Front-End (Swing):
1. **Adicionar dados** - Clique nos botões de adicionar em cada tela
2. **Listar dados** - Os dados são carregados automaticamente do banco
3. **Atualizar dados** - Use os métodos `update` dos services
4. **Remover dados** - Use os métodos `remove` dos services

### Mensagens de Feedback:
- ✅ **Sucesso** - Mensagens verdes confirmando operações
- ❌ **Erro** - Mensagens vermelhas com detalhes do erro

## 🔧 Configuração da API:

A URL base da API está configurada em `HttpClient.java`:
```java
private static final String BASE_URL = "http://localhost:8080/api";
```

Se precisar mudar a porta ou o host, edite esta variável.

## 📝 Endpoints Mapeados:

| Entidade | Endpoint Base | Operações |
|----------|---------------|-----------|
| Pessoas | `/api/v1/pessoas` | GET, POST, PUT, DELETE |
| Produtos | `/api/produtos` | GET, POST, PUT, DELETE |
| Preços | `/api/precos` | GET, POST, PUT, DELETE |
| Acessos | `/api/acessos` | GET, POST, PUT, DELETE |
| Contatos | `/api/contatos` | GET, POST, PUT, DELETE |
| Custos | `/api/custos` | GET, POST, PUT, DELETE |
| Estoques | `/api/estoques` | GET, POST, PUT, DELETE |

## 🎯 Exemplo de Uso:

### Adicionar uma Pessoa no Front-End:
```java
PessoaService pessoaService = new PessoaService();
Pessoa pessoa = new Pessoa("João Silva", "12345678900", "2000-01-15", "FUNCIONARIO", "USER");
pessoaService.addPessoa(pessoa);
```

Isso irá:
1. Criar um `PessoaRequest` com os dados
2. Enviar uma requisição POST para `http://localhost:8080/api/v1/pessoas`
3. Salvar no banco de dados PostgreSQL
4. Exibir mensagem de sucesso/erro

## ⚠️ Observações Importantes:

1. **Back-end deve estar rodando** antes de usar o front-end
2. **PostgreSQL deve estar ativo** na porta 5432
3. **Formato de datas** para Pessoa: `yyyy-MM-dd` (ex: "2000-01-15")
4. **Formato de datas** para Preço: `yyyy-MM-dd` (data) e `HH:mm:ss` (hora)

## 🐛 Troubleshooting:

### Erro de Conexão
- Verifique se o back-end está rodando
- Verifique se a URL está correta no `HttpClient.java`

### Erro 404
- Certifique-se de que os endpoints estão corretos
- Verifique no Swagger se os endpoints estão disponíveis

### Erro 400 (Bad Request)
- Verifique se todos os campos obrigatórios estão preenchidos
- Verifique o formato das datas

### Erro 500 (Internal Server Error)
- Verifique os logs do back-end
- Verifique se o banco de dados está acessível

## 📚 Tecnologias Utilizadas:

### Front-End:
- Java 17
- Swing (GUI)
- Jackson (JSON)
- Java HTTP Client

### Back-End:
- Spring Boot 3.4.0
- Spring Data JPA
- PostgreSQL
- Swagger/OpenAPI
- Bean Validation

## 🎓 Próximos Passos:

1. **Melhorar UI** - Adicionar tabelas para exibir listas
2. **Adicionar validações** - Validar campos antes de enviar
3. **Implementar edição** - Criar telas de edição de dados
4. **Adicionar autenticação** - Implementar login/logout
5. **Melhorar tratamento de erros** - Exibir mensagens mais específicas

## ✅ Status da Integração:

- [x] Cliente HTTP criado
- [x] DTOs mapeados
- [x] Services integrados
- [x] Operações CRUD funcionando
- [x] Comunicação com banco de dados
- [x] Mensagens de feedback

---

**Desenvolvido para o projeto PDV Posto de Combustível - 2025**

