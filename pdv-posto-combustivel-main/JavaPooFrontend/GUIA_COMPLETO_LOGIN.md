# 🔐 GUIA COMPLETO - Sistema de Login e Cadastro

## 📦 O QUE FOI CRIADO

Foram criados 3 novos arquivos no seu projeto:

### 1. SessionManager.java
**Local**: `src/main/java/br/com/PdvFrontEnd/util/SessionManager.java`

**Função**: Gerencia a sessão do usuário e salva/valida credenciais

**Métodos principais**:
- `getInstance()` - Obtém a instância única do gerenciador
- `login(username, userId)` - Registra o login do usuário
- `logout()` - Faz logout
- `saveCredentials(username, password)` - Salva as credenciais em arquivo
- `checkCredentials(username, password)` - Valida login
- `userExists()` - Verifica se já existe usuário cadastrado

### 2. LoginView.java
**Local**: `src/main/java/br/com/PdvFrontEnd/view/LoginView.java`

**Função**: Tela de login do sistema

**Componentes**:
- Campo de texto para usuário
- Campo de senha
- Botão "Entrar"
- Botão "Cadastrar" (vai para tela de cadastro)

**Validações**:
- Verifica se os campos estão preenchidos
- Valida as credenciais com o arquivo salvo
- Mostra mensagens de erro apropriadas

### 3. RegisterView.java
**Local**: `src/main/java/br/com/PdvFrontEnd/view/RegisterView.java`

**Função**: Tela de cadastro de novo usuário

**Componentes**:
- Campo de texto para nome completo
- Campo de texto para usuário
- Campo de senha
- Campo de confirmação de senha
- Botão "Cadastrar"
- Botão "Já tenho cadastro" (volta para login)

**Validações**:
- Todos os campos obrigatórios
- Usuário mínimo 3 caracteres
- Senha mínimo 4 caracteres
- Senha e confirmação devem ser iguais

### 4. run-login.bat (BONUS!)
**Local**: `run-login.bat` (raiz do projeto)

**Função**: Script para executar o sistema facilmente

---

## 🚀 COMO USAR

### Opção 1: Usando o Script Batch (MAIS FÁCIL)

1. Navegue até a pasta do projeto:
   ```
   C:\Users\Aluno\Documents\pdv-posto-combustivel-main\JavaPoo-Front-End-main
   ```

2. Dê duplo clique no arquivo `run-login.bat`

3. Aguarde o sistema compilar e abrir a janela de login

### Opção 2: Via PowerShell

```powershell
cd C:\Users\Aluno\Documents\pdv-posto-combustivel-main\JavaPoo-Front-End-main
mvn clean compile exec:java '-Dexec.mainClass=br.com.PdvFrontEnd.view.LoginView'
```

### Opção 3: Via IDE (IntelliJ IDEA)

1. Abra o projeto no IntelliJ
2. Navegue: `src/main/java/br/com/PdvFrontEnd/view/LoginView.java`
3. Clique com botão direito no arquivo
4. Escolha "Run 'LoginView.main()'"

---

## 📖 FLUXO DE USO DETALHADO

### PRIMEIRA VEZ (Cadastro)

**Passo 1**: Execute o sistema
```powershell
# Execute o run-login.bat OU use o comando PowerShell
```

**Passo 2**: O sistema detecta que não há usuário cadastrado e abre automaticamente a tela de **CADASTRO**

**Passo 3**: Preencha o formulário:
- **Nome Completo**: Digite seu nome (ex: João Silva)
- **Usuário**: Digite um usuário com no mínimo 3 caracteres (ex: joao123)
- **Senha**: Digite uma senha com no mínimo 4 caracteres (ex: 1234)
- **Confirmar Senha**: Digite a mesma senha novamente

**Passo 4**: Clique no botão **"Cadastrar"**

**Passo 5**: Você verá uma mensagem de sucesso e será redirecionado para a tela de **LOGIN**

### FAZENDO LOGIN

**Passo 1**: Na tela de login, digite:
- **Usuário**: O usuário que você cadastrou (ex: joao123)
- **Senha**: A senha que você cadastrou (ex: 1234)

**Passo 2**: Clique em **"Entrar"** (ou pressione Enter)

**Passo 3**: Se as credenciais estiverem corretas:
- Você verá uma mensagem "Login realizado com sucesso!"
- O sistema abrirá a tela principal com os botões de gerenciamento:
  - Gerenciar Pessoas
  - Gerenciar Preços
  - Gerenciar Produtos
  - Gerenciar Custos
  - Gerenciar Estoques
  - Gerenciar Acessos
  - Gerenciar Contatos

**Passo 4**: Agora você pode usar o sistema normalmente!

---

## 💾 ONDE FICAM SALVAS AS CREDENCIAIS?

As credenciais são salvas em um arquivo chamado `user_config.properties` na raiz do projeto:

```
C:\Users\Aluno\Documents\pdv-posto-combustivel-main\JavaPoo-Front-End-main\user_config.properties
```

**Exemplo do conteúdo**:
```properties
#User Credentials
#Wed Oct 30 20:00:00 BRT 2025
password=1234
username=joao123
```

---

## 🔧 PROBLEMAS COMUNS E SOLUÇÕES

### ❌ Problema: "Usuário ou senha incorretos"

**Causa**: As credenciais digitadas não conferem com as salvas

**Solução**:
1. Verifique se digitou o usuário e senha corretamente
2. Se esqueceu a senha, delete o arquivo `user_config.properties`:
   ```powershell
   cd C:\Users\Aluno\Documents\pdv-posto-combustivel-main\JavaPoo-Front-End-main
   Remove-Item user_config.properties
   ```
3. Execute o sistema novamente e faça um novo cadastro

### ❌ Problema: "Por favor, preencha todos os campos"

**Causa**: Algum campo está vazio

**Solução**: Preencha todos os campos obrigatórios

### ❌ Problema: "O usuário deve ter no mínimo 3 caracteres"

**Causa**: Usuário muito curto

**Solução**: Digite um usuário com pelo menos 3 caracteres

### ❌ Problema: "A senha deve ter no mínimo 4 caracteres"

**Causa**: Senha muito curta

**Solução**: Digite uma senha com pelo menos 4 caracteres

### ❌ Problema: "As senhas não conferem"

**Causa**: Os campos "Senha" e "Confirmar Senha" estão diferentes

**Solução**: Digite a mesma senha nos dois campos

### ❌ Problema: Sistema não abre a janela

**Causa**: Pode estar executando em background

**Solução**: 
1. Verifique se a janela não está minimizada ou atrás de outras
2. Compile novamente: `mvn clean compile`
3. Use o script `run-login.bat`

---

## 🔄 TESTANDO O SISTEMA

### Teste 1: Cadastro
1. Execute o sistema pela primeira vez
2. Deve abrir a tela de cadastro
3. Preencha os dados e cadastre
4. Deve redirecionar para login

### Teste 2: Login Correto
1. Digite o usuário e senha corretos
2. Clique em Entrar
3. Deve abrir o sistema de gerenciamento

### Teste 3: Login Incorreto
1. Digite usuário ou senha errados
2. Deve aparecer mensagem de erro
3. O campo de senha deve ser limpo

### Teste 4: Voltar de Cadastro para Login
1. Na tela de cadastro, clique em "Já tenho cadastro"
2. Deve voltar para a tela de login

### Teste 5: Ir de Login para Cadastro
1. Na tela de login, clique em "Cadastrar"
2. Deve ir para a tela de cadastro

---

## 🎨 CARACTERÍSTICAS VISUAIS

O sistema segue o mesmo padrão de design do resto da aplicação:

### Cores
- **Azul Primário**: `#3498db` (Botões principais)
- **Azul Escuro**: `#2c3e50` (Fundo)
- **Laranja**: `#e67e22` (Botões secundários)
- **Texto**: Branco `#FFFFFF`

### Efeitos
- Hover nos botões (muda de cor ao passar o mouse)
- Cursor de mão ao passar sobre botões
- Bordas arredondadas
- Sombras suaves

### Fontes
- **Família**: Segoe UI
- **Título**: Bold, 28px
- **Botões**: Bold, 14px
- **Labels**: Regular, 14px

---

## 📊 ESTRUTURA DO CÓDIGO

```
JavaPoo-Front-End-main/
│
├── src/main/java/br/com/PdvFrontEnd/
│   │
│   ├── util/
│   │   └── SessionManager.java ← Gerencia sessão e credenciais
│   │
│   └── view/
│       ├── LoginView.java ← Tela de login
│       ├── RegisterView.java ← Tela de cadastro
│       └── MainApp.java ← Tela principal (já existia)
│
├── user_config.properties ← Arquivo de credenciais (criado automaticamente)
│
└── run-login.bat ← Script para executar facilmente
```

---

## 🔐 SEGURANÇA

⚠️ **IMPORTANTE**: Este é um sistema de autenticação LOCAL e SIMPLES

### Limitações de Segurança

1. **Senha em texto plano**: As senhas são salvas sem criptografia
2. **Um único usuário**: Suporta apenas um usuário por vez
3. **Sem token**: Não usa tokens de autenticação
4. **Sem expiração**: A sessão não expira

### Para Produção, você deveria:

1. ✅ Criptografar senhas (usar BCrypt ou similar)
2. ✅ Implementar autenticação via API do backend
3. ✅ Usar tokens JWT
4. ✅ Implementar níveis de acesso (roles)
5. ✅ Adicionar timeout de sessão
6. ✅ Implementar recuperação de senha
7. ✅ Adicionar logs de acesso
8. ✅ Implementar bloqueio após tentativas falhas

---

## 🎯 PRÓXIMOS PASSOS (Melhorias Futuras)

### Funcionalidades que podem ser adicionadas:

1. **Botão de Logout**
   - Adicionar botão no MainApp para deslogar
   - Voltar para tela de login

2. **Múltiplos Usuários**
   - Salvar em banco de dados ou arquivo JSON
   - Cada usuário com suas próprias credenciais

3. **Criptografia de Senha**
   - Usar biblioteca de hash (BCrypt, SHA-256)
   - Senhas nunca ficam expostas

4. **Recuperação de Senha**
   - Email de recuperação
   - Perguntas de segurança

5. **Níveis de Acesso**
   - Admin (acesso total)
   - Usuário (acesso limitado)
   - Operador (apenas consulta)

6. **Lembrar-me**
   - Checkbox para lembrar login
   - Preencher automaticamente

7. **Histórico de Acessos**
   - Log de quando usuário fez login
   - Registro de ações realizadas

8. **Timeout de Sessão**
   - Logout automático após inatividade
   - Aviso antes de deslogar

---

## 📞 SUPORTE

Se você tiver problemas:

1. **Verifique a compilação**:
   ```powershell
   mvn clean compile
   ```

2. **Verifique se o arquivo existe**:
   ```powershell
   Test-Path user_config.properties
   ```

3. **Delete o arquivo de configuração** (para recomeçar):
   ```powershell
   Remove-Item user_config.properties
   ```

4. **Veja os logs do Maven** para mais detalhes

---

## ✅ CHECKLIST DE IMPLEMENTAÇÃO

- [x] Criar SessionManager para gerenciar sessão
- [x] Criar LoginView com validação
- [x] Criar RegisterView com validação
- [x] Salvar credenciais localmente
- [x] Validar campos obrigatórios
- [x] Validar tamanho mínimo de usuário e senha
- [x] Confirmar senha no cadastro
- [x] Redirecionar para MainApp após login
- [x] Detectar primeiro acesso
- [x] Interface gráfica moderna
- [x] Script batch para execução fácil
- [x] Documentação completa
- [x] Compilação bem-sucedida

---

## 🎉 CONCLUSÃO

Seu sistema de login está **PRONTO** e **FUNCIONANDO**!

### Para executar:
1. Dê duplo clique em `run-login.bat`
   
   **OU**

2. Execute no PowerShell:
   ```powershell
   cd C:\Users\Aluno\Documents\pdv-posto-combustivel-main\JavaPoo-Front-End-main
   mvn clean compile exec:java '-Dexec.mainClass=br.com.PdvFrontEnd.view.LoginView'
   ```

### O que acontece:
- **Primeira vez**: Abre tela de cadastro → você se cadastra → faz login → usa o sistema
- **Próximas vezes**: Abre tela de login → você faz login → usa o sistema

**🎊 Divirta-se usando seu sistema com autenticação!**

---

*Documentação criada em 30/10/2025*
*Sistema de Login - Front-End PDV - Posto de Combustível*

