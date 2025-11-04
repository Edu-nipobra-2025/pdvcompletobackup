# COMO USAR O SISTEMA PDV - FORMA SIMPLES

## 🚀 Como Executar (Apenas 2 passos!)

### 1️⃣ Iniciar o Backend
Abra o arquivo no IntelliJ IDEA:
```
pdv-posto-combustivel/src/main/java/com/br/pdvpostocombustivel/PdvpostocombustivelApplication.java
```
**Execute este arquivo (clique com botão direito > Run)**

### 2️⃣ Iniciar o Frontend
Abra o arquivo no IntelliJ IDEA:
```
JavaPoo-Front-End-main/src/main/java/br/com/PdvFrontEnd/view/MainApp.java
```
**Execute este arquivo (clique com botão direito > Run)**

## 📊 Visualizar os Dados

### PostgreSQL (Banco de dados principal)
- As informações serão salvas automaticamente no PostgreSQL
- Você pode consultar usando pgAdmin ou qualquer cliente PostgreSQL
- **Banco:** pdvpostocombustivel
- **Usuário:** postgres
- **Senha:** Sidney123@

### H2 Console (Visualização rápida)
1. Abra o navegador
2. Acesse: `http://localhost:8080/h2-console`
3. Configure a conexão:
   - **JDBC URL:** `jdbc:postgresql://localhost:5432/pdvpostocombustivel`
   - **Username:** `postgres`
   - **Password:** `Sidney123@`
4. Clique em "Connect"
5. Agora você pode ver todas as tabelas e dados!

## 📝 Dados de Exemplo
O sistema já vem com dados de exemplo que são inseridos automaticamente:
- 4 Pessoas (João Silva, Maria Santos, Pedro Oliveira, Empresa XYZ)
- 4 Contatos
- 2 Acessos (usuários do sistema)
- 5 Produtos (Gasolina, Etanol, Diesel, Óleo, Água)
- 5 Preços
- 5 Estoques

## ✅ Tudo Funcionando!
- ✅ Dados aparecem no PostgreSQL
- ✅ Dados aparecem no H2 Console
- ✅ Frontend conecta com o Backend
- ✅ Todas as operações CRUD funcionando

## 🗑️ Arquivos Removidos
Os seguintes arquivos .bat foram removidos pois não são mais necessários:
- compilar_backend.bat
- iniciar_backend_h2.bat
- iniciar_backend_postgres.bat
- iniciar_frontend.bat
- testar_backend.bat

**Agora é só executar os 2 arquivos Java principais e pronto! 🎉**

