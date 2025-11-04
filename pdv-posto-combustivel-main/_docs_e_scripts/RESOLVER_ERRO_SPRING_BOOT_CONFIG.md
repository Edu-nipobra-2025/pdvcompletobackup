# Como Resolver Erro "Unknown run configuration type SpringBootApplicationConfigurationType"

## Problema
Ao tentar executar o backend no IntelliJ IDEA, aparece o erro:
```
Error running 'Backend - PDV Posto Combustivel'
Unknown run configuration type SpringBootApplicationConfigurationType
```

---

## ⚠️ ERRO: Porta 8080 Já Está em Uso

Se ao tentar executar o backend aparecer o erro:
```
Web server failed to start. Port 8080 was already in use.
```

### Solução Rápida: Usar o Script Automático

Execute o script que criei para você:
```cmd
cd "C:\Users\sidney\Documents\faculdades 2025\pdvpostocombustivel\_docs_e_scripts"
LIBERAR_PORTA_8080.bat
```

Este script irá:
1. Identificar automaticamente qual processo está usando a porta 8080
2. Encerrar o processo
3. Liberar a porta para o backend

### Solução Manual

Se preferir fazer manualmente:

**Passo 1: Identificar o processo**
```cmd
netstat -ano | findstr :8080
```

**Passo 2: Encerrar o processo** (substitua `PID` pelo número que apareceu)
```cmd
taskkill /F /PID [número_do_PID]
```

**Exemplo:**
```cmd
taskkill /F /PID 30600
```

### Dica Importante
- Se você executar o backend múltiplas vezes sem parar a instância anterior, a porta 8080 ficará ocupada
- No IntelliJ, sempre use o botão **vermelho quadrado** (Stop) para parar o backend antes de executar novamente
- Se não aparecer o botão vermelho, use o script `LIBERAR_PORTA_8080.bat`

---

## Solução 1: Recriar a Configuração de Execução (RECOMENDADO)

### Passo 1: Excluir a Configuração Atual
1. Clique no dropdown "Backend - PDV Posto Combustivel" no topo direito
2. Clique em "Edit Configurations..."
3. Selecione "Backend - PDV Posto Combustivel" na lista à esquerda
4. Clique no botão "-" (menos) para excluir
5. Clique em "OK"

### Passo 2: Criar Nova Configuração Spring Boot
1. Clique em "Add Configuration..." no topo direito
2. Clique no botão "+" (mais)
3. Selecione "Spring Boot" na lista
   - **Se não aparecer "Spring Boot"**: vá para a Solução 2 primeiro
4. Configure:
   - **Name**: Backend - PDV Posto Combustivel
   - **Main class**: `src.main.java.com.br.pdvpostocombustivel.PdvpostocombustivelApplication`
   - **Module**: `pdv-posto-combustivel`
   - **Working directory**: `C:\Users\sidney\Documents\faculdades 2025\pdvpostocombustivel\pdv-posto-combustivel`
5. Clique em "OK"

### Passo 3: Executar
1. Clique no botão "Run" (triângulo verde)
2. A aplicação deve iniciar na porta 8080

---

## Solução 2: Verificar/Instalar Plugin Spring Boot

### Passo 1: Verificar Plugins
1. Vá em: **File** → **Settings** (ou **Ctrl+Alt+S**)
2. Clique em **Plugins** no menu lateral
3. Procure por "Spring Boot" na barra de busca
4. Certifique-se de que os seguintes plugins estão **instalados e habilitados**:
   - **Spring Boot**
   - **Spring**
   - **Spring MVC**
5. Se não estiverem instalados, clique em "Install" e reinicie o IDE
6. Se estiverem desabilitados, marque o checkbox e reinicie o IDE

---

## Solução 3: Invalidar Caches do IntelliJ IDEA

### Passo a Passo
1. Vá em: **File** → **Invalidate Caches...**
2. Marque todas as opções:
   - ✓ Invalidate and Restart
   - ✓ Clear file system cache and Local History
   - ✓ Clear VCS Log caches and indexes
   - ✓ Clear downloaded shared indexes
3. Clique em "Invalidate and Restart"
4. Aguarde o IDE reiniciar e reindexar o projeto
5. Após reiniciar, tente a **Solução 1** novamente

---

## Solução 4: Reimportar Projeto Maven

### Passo a Passo
1. Clique com botão direito no arquivo `pom.xml` do projeto `pdv-posto-combustivel`
2. Selecione: **Maven** → **Reload Project**
3. Aguarde o Maven baixar dependências e reconfigurar o projeto
4. Após concluir, tente a **Solução 1** novamente

---

## Solução 5: Executar pelo Terminal (Alternativa)

Se as soluções acima não funcionarem, você pode executar o backend diretamente pelo terminal:

### Opção A: Usando Maven
```bash
cd "C:\Users\sidney\Documents\faculdades 2025\pdvpostocombustivel\pdv-posto-combustivel"
mvn spring-boot:run
```

### Opção B: Usando JAR compilado
```bash
cd "C:\Users\sidney\Documents\faculdades 2025\pdvpostocombustivel\pdv-posto-combustivel"
mvn clean package
java -jar target\pdvpostocombustivel-0.0.1-SNAPSHOT.jar
```

### Opção C: Usando o Script BAT (já existe no projeto)
```bash
cd "C:\Users\sidney\Documents\faculdades 2025\pdvpostocombustivel\_docs_e_scripts"
EXECUTAR_BACKEND.bat
```

---

## Solução 6: Criar Configuração Application (Alternativa)

Se "Spring Boot" não aparecer nas opções, crie uma configuração "Application":

1. Clique em "Add Configuration..."
2. Clique no botão "+" (mais)
3. Selecione "Application"
4. Configure:
   - **Name**: Backend - PDV Posto Combustivel
   - **Main class**: `src.main.java.com.br.pdvpostocombustivel.PdvpostocombustivelApplication`
   - **Module**: `pdv-posto-combustivel.main`
   - **Working directory**: `C:\Users\sidney\Documents\faculdades 2025\pdvpostocombustivel\pdv-posto-combustivel`
   - **JRE**: Selecione o JDK instalado (Java 17 ou superior recomendado)
5. Clique em "OK"

---

## Verificar se o Backend Está Rodando

Após iniciar o backend (por qualquer método), verifique se está funcionando:

1. Abra o navegador
2. Acesse: `http://localhost:8080`
3. Deve aparecer a página da API ou erro 404 (normal se não houver página raiz)
4. Teste um endpoint: `http://localhost:8080/api/produtos` (ou outro endpoint disponível)

---

## Ordem Recomendada de Tentativas

1. **Solução 1**: Recriar configuração de execução
2. **Solução 3**: Invalidar caches
3. **Solução 2**: Verificar plugins Spring Boot
4. **Solução 4**: Reimportar projeto Maven
5. **Solução 5**: Executar pelo terminal (sempre funciona)
6. **Solução 6**: Configuração Application (último recurso)

---

## Observações

- O erro geralmente é resolvido com a **Solução 1** + **Solução 3**
- Se o projeto funcionar pelo terminal mas não pelo IDE, o problema é apenas configuração do IntelliJ
- Certifique-se de que o banco de dados PostgreSQL está rodando antes de iniciar o backend
- Verifique o arquivo `application.properties` para confirmar as configurações de conexão

---

## Suporte Adicional

Se nenhuma solução funcionar:
1. Verifique a versão do IntelliJ IDEA (deve ser Community ou Ultimate atualizada)
2. Verifique a versão do Java (JDK 17 ou superior recomendado)
3. Verifique se há erros no arquivo `pom.xml`
4. Consulte os logs do IntelliJ em: **Help** → **Show Log in Explorer**
