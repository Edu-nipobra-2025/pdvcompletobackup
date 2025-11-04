# Sistema de Login e Cadastro - PDV Posto de Combustível

## ✅ O que foi implementado

Foi adicionado um **sistema completo de login e cadastro** que aparece ANTES da tela de gerenciamento.

### 📁 Arquivos Criados:

1. **LoginView.java** - Tela de login
2. **RegisterView.java** - Tela de cadastro de novos usuários
3. **AuthService.java** - Serviço de autenticação
4. **User.java** - Modelo de usuário

### 🔄 Como funciona:

1. Quando você executar o frontend, a **tela de LOGIN** aparece primeiro
2. Se você não tem cadastro, clique em **"Cadastrar"**
3. Preencha os dados: Nome Completo, Email, Usuário e Senha
4. Após cadastrar, você volta para a tela de login
5. Faça login com seu usuário e senha
6. **Somente após o login bem-sucedido** a tela de gerenciamento aparece

### 💾 Armazenamento:

Os usuários são salvos em um arquivo local chamado `users.dat` na raiz do projeto.
Isso significa que seus cadastros ficam salvos mesmo depois de fechar o programa.

### 🎨 Interface:

- Tela de Login: Campos de Usuário e Senha
- Botões: "Entrar" e "Cadastrar"
- Tela de Cadastro: Nome Completo, Email, Usuário, Senha e Confirmação de Senha
- Cores mantidas no padrão do seu sistema (azul, verde, laranja)

### ✅ Validações implementadas:

- Todos os campos obrigatórios
- Senha mínima de 4 caracteres
- Confirmação de senha
- Email válido (deve conter @)
- Usuário único (não permite duplicados)

## 📝 Alterações nas pastas:

✅ **pdv-posto-combustivel** → renomeado para **backend**
✅ **JavaPoo-Front-End-main** → renomeado para **frontend**
✅ **pom.xml** atualizado com os novos nomes dos módulos

## 🚀 Como executar:

### Backend:
```cmd
cd backend
mvnw spring-boot:run
```

### Frontend (com login):
```cmd
cd frontend
mvn clean compile
mvn exec:java -Dexec.mainClass="br.com.PdvFrontEnd.view.MainApp"
```

## 🔐 Primeiro Acesso:

1. Execute o frontend
2. Clique em "Cadastrar"
3. Crie seu primeiro usuário
4. Faça login
5. Aproveite o sistema!

---
**Data de Implementação:** 30/10/2025
**Desenvolvido para:** Projeto Acadêmico 2025

