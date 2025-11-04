# Como Executar o Backend Clicando no Main (Igual ao Frontend)

## Problema
Você quer executar o backend clicando no triângulo verde ▶️ ao lado do método `main` da classe `PdvpostocombustivelApplication`, mas está dando erro de configuração.

---

## ✅ Solução: Limpar Configurações Corrompidas

### Passo 1: Invalidar Caches do IntelliJ IDEA
1. No IntelliJ, vá em: **File** → **Invalidate Caches...**
2. Marque **TODAS** as opções disponíveis
3. Clique em **"Invalidate and Restart"**
4. Aguarde o IDE reiniciar completamente (pode demorar alguns minutos)

---

### Passo 2: Excluir Configuração Corrompida
Após o IntelliJ reiniciar:

1. Clique no dropdown **"Backend - PDV Posto Combustivel"** no canto superior direito
2. Clique em **"Edit Configurations..."**
3. Na lista à esquerda, selecione **"Backend - PDV Posto Combustivel"**
4. Clique no botão **"−"** (menos) para **EXCLUIR** esta configuração
5. Clique em **"OK"**

---

### Passo 3: Executar Pelo Main (Cria Configuração Automaticamente)

Agora sim! Vá até o arquivo `PdvpostocombustivelApplication.java`:

1. Abra o arquivo: 
   ```
   pdv-posto-combustivel/src/main/java/com/br/pdvpostocombustivel/PdvpostocombustivelApplication.java
   ```

2. Localize o método `main`:
   ```java
   public static void main(String[] args) {
       SpringApplication.run(PdvpostocombustivelApplication.class, args);
   }
   ```

3. **Clique no triângulo verde ▶️** que aparece ao lado de `public static void main`

4. Selecione: **"Run 'PdvpostocombustivelApplication.main()'"**

5. O IntelliJ vai **criar automaticamente** uma configuração nova e correta!

6. O backend deve iniciar normalmente na porta 8080 🎉

---

## 🎯 Após Fazer Isso Uma Vez

Depois de executar pelo `main` uma vez:

- ✅ O triângulo verde ▶️ no topo direito vai funcionar normalmente
- ✅ Você pode executar o backend igual faz com o frontend
- ✅ A configuração ficará salva e funcionando corretamente

---

## 🔧 Se Ainda Não Funcionar

### Opção A: Criar Configuração Manualmente

1. Clique em **"Add Configuration..."** no topo direito
2. Clique no **"+"** (mais)
3. Selecione **"Application"** (NÃO selecione "Spring Boot" se der erro)
4. Configure:
   - **Name**: `Backend - PDV Posto Combustivel`
   - **Main class**: `src.main.java.com.br.pdvpostocombustivel.PdvpostocombustivelApplication`
   - **Module**: Selecione `pdv-posto-combustivel.main` ou `pdv-posto-combustivel`
   - **Working directory**: `C:\Users\sidney\Documents\faculdades 2025\pdvpostocombustivel\pdv-posto-combustivel`
5. Clique em **"OK"**
6. Clique no triângulo verde ▶️ no topo direito

---

### Opção B: Verificar se Plugin Spring Boot Está Ativado

1. Vá em: **File** → **Settings** (ou `Ctrl+Alt+S`)
2. Clique em **Plugins**
3. Procure por **"Spring Boot"**
4. Certifique-se de que está **instalado e habilitado** (checkbox marcado)
5. Se não estiver, clique em **"Install"** e reinicie o IDE
6. Após reiniciar, tente executar pelo `main` novamente

---

### Opção C: Reimportar Projeto Maven

1. Clique com botão direito no arquivo `pom.xml` (do projeto `pdv-posto-combustivel`)
2. Selecione: **Maven** → **Reload Project**
3. Aguarde o Maven reconfigurar o projeto
4. Tente executar pelo `main` novamente

---

## 📝 Resumo: Ordem de Tentativas

1. ✅ **Invalidar Caches** → Reiniciar IDE
2. ✅ **Excluir configuração corrompida**
3. ✅ **Executar clicando no triângulo verde ao lado do `main`**
4. Se não funcionar → **Criar configuração manualmente (Opção A)**
5. Se não funcionar → **Verificar plugins (Opção B)**
6. Se não funcionar → **Reimportar Maven (Opção C)**

---

## 🎉 Resultado Final

Após seguir estes passos, você poderá:

- ✅ Clicar no triângulo verde ▶️ ao lado do método `main`
- ✅ Executar o backend igual executa o frontend
- ✅ Usar o botão vermelho ⬛ para parar
- ✅ Usar o ícone ⟳ (reload) para reiniciar

**Igual ao frontend! 🚀**

---

## ⚠️ Lembre-se

Antes de executar o backend, certifique-se de que:
- ✅ O PostgreSQL está rodando
- ✅ A porta 8080 está livre (use `LIBERAR_PORTA_8080.bat` se necessário)
- ✅ O banco de dados `pdv_posto_combustivel` existe e está configurado

