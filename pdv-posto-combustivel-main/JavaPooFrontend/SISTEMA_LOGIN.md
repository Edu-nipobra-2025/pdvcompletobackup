# Sistema de Login - Front-End PDV

## 📋 Descrição

Sistema de login e cadastro local criado para o front-end do PDV. As credenciais são salvas localmente em um arquivo `user_config.properties`.

## 🚀 Como Usar

### Primeira Execução - Cadastro

1. Execute a classe `LoginView.java`:
   ```powershell
   cd C:\Users\Aluno\Documents\pdv-posto-combustivel-main\JavaPoo-Front-End-main
   mvn exec:java -Dexec.mainClass="br.com.PdvFrontEnd.view.LoginView"
   ```

2. Como é a primeira vez, você será direcionado automaticamente para a tela de **Cadastro**

3. Preencha os campos:
   - **Nome Completo**: Seu nome
   - **Usuário**: Mínimo 3 caracteres
   - **Senha**: Mínimo 4 caracteres
   - **Confirmar Senha**: Repita a senha

4. Clique em **Cadastrar**

5. Você será redirecionado para a tela de **Login**

### Login

1. Na tela de login, digite:
   - **Usuário**: O usuário que você cadastrou
   - **Senha**: A senha que você cadastrou

2. Clique em **Entrar** (ou pressione Enter)

3. Se as credenciais estiverem corretas, você será direcionado para o **Sistema de Gerenciamento PDV**

### Executar o Sistema Completo

Para iniciar o sistema com login, execute:

```powershell
cd C:\Users\Aluno\Documents\pdv-posto-combustivel-main\JavaPoo-Front-End-main
mvn clean compile exec:java -Dexec.mainClass="br.com.PdvFrontEnd.view.LoginView"
```

## 📁 Arquivos Criados

### 1. `SessionManager.java` (util)
- Gerencia a sessão do usuário
- Salva e valida credenciais localmente
- Verifica se usuário já existe

### 2. `LoginView.java` (view)
- Tela de login
- Validação de credenciais
- Redirecionamento para tela principal após login

### 3. `RegisterView.java` (view)
- Tela de cadastro
- Validação de campos (nome, usuário, senha)
- Confirmação de senha
- Salva credenciais localmente

## 🔒 Segurança

⚠️ **IMPORTANTE**: Este é um sistema de autenticação LOCAL e SIMPLES, apenas para controle de acesso básico. As senhas são armazenadas em texto plano no arquivo `user_config.properties`.

**Para um ambiente de produção, você deve:**
- Usar criptografia (hash) para as senhas
- Implementar autenticação via API do backend
- Usar tokens JWT ou OAuth2
- Implementar níveis de acesso (roles)

## 📝 Funcionalidades

✅ Cadastro de novo usuário
✅ Login com validação
✅ Salvamento local de credenciais
✅ Redirecionamento automático para cadastro (primeira vez)
✅ Interface gráfica moderna e responsiva
✅ Validações de campos obrigatórios
✅ Validação de tamanho mínimo de usuário e senha
✅ Confirmação de senha no cadastro

## 🎨 Interface

O sistema segue o mesmo padrão visual do resto da aplicação:
- **Cor Primária**: Azul (#3498db)
- **Cor Secundária**: Azul Escuro (#2c3e50)
- **Cor de Destaque**: Laranja (#e67e22)
- **Fonte**: Segoe UI

## 🔄 Fluxo de Uso

```
Primeira Vez:
LoginView → Detecta que não há usuário → RegisterView → Cadastro → LoginView → Login → MainApp

Acessos Seguintes:
LoginView → Login → MainApp
```

## 📍 Localização dos Arquivos

```
JavaPoo-Front-End-main/
└── src/
    └── main/
        └── java/
            └── br/
                └── com/
                    └── PdvFrontEnd/
                        ├── util/
                        │   └── SessionManager.java (NOVO)
                        └── view/
                            ├── LoginView.java (NOVO)
                            ├── RegisterView.java (NOVO)
                            └── MainApp.java (existente)
```

## 🛠️ Possíveis Melhorias Futuras

- [ ] Integração com backend para autenticação real
- [ ] Criptografia de senhas
- [ ] Recuperação de senha
- [ ] Múltiplos usuários
- [ ] Níveis de acesso (admin, usuário, etc.)
- [ ] Tempo de expiração de sessão
- [ ] Logout automático após inatividade
- [ ] Histórico de acessos

## ❓ Troubleshooting

### Problema: Não consigo fazer login
**Solução**: Verifique se você cadastrou um usuário primeiro. O arquivo `user_config.properties` deve existir na raiz do projeto.

### Problema: Esqueci minha senha
**Solução**: Delete o arquivo `user_config.properties` da raiz do projeto e faça um novo cadastro.

### Problema: Erro ao compilar
**Solução**: Execute `mvn clean compile` para recompilar o projeto.

---

**Desenvolvido para o Sistema PDV - Posto de Combustível**

