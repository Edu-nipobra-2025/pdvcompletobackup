# GUIA RÁPIDO - Como Limpar o Banco de Dados

## Passo a Passo no pgAdmin:

1. **Abra o pgAdmin 4**

2. **Conecte ao servidor PostgreSQL:**
   - Expanda "Servers" no painel esquerdo
   - Clique em "PostgreSQL" (vai pedir a senha: Sidney123@)

3. **Selecione o banco de dados:**
   - Expanda "Databases"
   - Clique com o botão direito em "pdvpostocombustivel"
   - Selecione "Query Tool" (ou pressione Alt+Shift+Q)

4. **Execute o script de limpeza:**
   - Na janela Query Tool que abriu
   - Clique no ícone "Open File" (pasta) ou pressione Ctrl+O
   - Navegue até: C:\Users\sidney\Documents\faculdades 2025\pdvpostocombustivel
   - Selecione o arquivo: LIMPAR_BANCO_DADOS.sql
   - Clique em "Executar" (ícone de play ▶) ou pressione F5

5. **Verifique os resultados:**
   - Você verá mensagens confirmando que todas as tabelas foram limpas
   - Todas as contagens devem mostrar "0 registros"

## Pronto!
Agora seu banco está completamente limpo e pronto para receber novos dados.

## Próximos Passos:
1. Execute o backend: PdvPostoCombustivelApplication.java
2. Execute o frontend: MainApp.java
3. Cadastre novos dados através da interface

---
**Observação:** Este script NÃO apaga as tabelas, apenas os dados dentro delas.
A estrutura do banco permanece intacta.

