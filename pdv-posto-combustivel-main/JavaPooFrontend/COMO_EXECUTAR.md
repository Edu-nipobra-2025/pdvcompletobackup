# 🚀 COMO EXECUTAR O SISTEMA - GUIA RÁPIDO

## ✅ SISTEMA CONFIGURADO

Agora o sistema está configurado para iniciar pelo login quando você executar o **MainApp.java** diretamente!

---

## 🎯 EXECUTAR PELA IDE (IntelliJ IDEA)

### Passo a Passo:

1. **Abra o projeto no IntelliJ IDEA**
   ```
   File → Open → Selecione a pasta:
   C:\Users\Aluno\Documents\pdv-posto-combustivel-main\JavaPoo-Front-End-main
   ```

2. **Aguarde a indexação do projeto** (barra de progresso no canto inferior)

3. **Navegue até MainApp.java**
   ```
   src/main/java/br/com/PdvFrontEnd/view/MainApp.java
   ```

4. **Execute de uma das formas:**

   **Forma 1 - Botão Direito:**
   - Clique com botão direito no arquivo `MainApp.java`
   - Selecione: `Run 'MainApp.main()'`

   **Forma 2 - Atalho:**
   - Abra o arquivo `MainApp.java`
   - Pressione: `Shift + F10` (Windows/Linux) ou `Ctrl + Shift + R` (Mac)

   **Forma 3 - Ícone Play:**
   - Abra o arquivo `MainApp.java`
   - Clique no ícone verde ▶️ ao lado do método `main()`

5. **Pronto!** 🎉
   - Sistema iniciará automaticamente
   - Se é primeira vez: Abre tela de CADASTRO
   - Se já tem usuário: Abre tela de LOGIN

---

## 🎯 EXECUTAR PELA IDE (Eclipse)

### Passo a Passo:

1. **Abra o projeto no Eclipse**
   ```
   File → Open Projects from File System
   Selecione: C:\Users\Aluno\Documents\pdv-posto-combustivel-main\JavaPoo-Front-End-main
   ```

2. **Aguarde a indexação do projeto**

3. **Navegue até MainApp.java**
   ```
   src/main/java → br.com.PdvFrontEnd.view → MainApp.java
   ```

4. **Execute:**
   - Clique com botão direito no arquivo
   - Selecione: `Run As → Java Application`

5. **Pronto!** 🎉

---

## 🎯 EXECUTAR VIA MAVEN (Terminal/PowerShell)

```powershell
cd C:\Users\Aluno\Documents\pdv-posto-combustivel-main\JavaPoo-Front-End-main
mvn clean compile exec:java -Dexec.mainClass=br.com.PdvFrontEnd.view.MainApp
```

---

## 🔄 FLUXO DO SISTEMA

```
Executa MainApp.java
        ↓
Sistema verifica: Existe usuário cadastrado?
        ↓
    ┌───────┴───────┐
    │               │
   NÃO             SIM
    │               │
    ↓               ↓
CADASTRO         LOGIN
    │               │
    ↓               │
  LOGIN ────────────┘
    │
    ↓
GERENCIAMENTO
    │
    ↓
Use o sistema!
```

---

## 📋 PRIMEIRA VEZ (Cadastro)

Quando você executar pela primeira vez:

1. ✅ Sistema abre automaticamente a tela de **CADASTRO**
2. ✅ Preencha: Nome, Usuário (min. 3 chars), Senha (min. 4 chars)
3. ✅ Clique em "Cadastrar"
4. ✅ Sistema redireciona para tela de **LOGIN**
5. ✅ Digite seu usuário e senha
6. ✅ Clique em "Entrar"
7. ✅ Sistema abre a tela de **GERENCIAMENTO**

---

## 📋 PRÓXIMAS VEZES (Login)

Quando você executar depois de cadastrado:

1. ✅ Sistema abre automaticamente a tela de **LOGIN**
2. ✅ Digite seu usuário e senha
3. ✅ Clique em "Entrar"
4. ✅ Sistema abre a tela de **GERENCIAMENTO**

---

## 🎨 TELAS DO SISTEMA

### 1. Tela de Cadastro
```
╔═══════════════════════════════════╗
║         📝 CADASTRO               ║
╠═══════════════════════════════════╣
║  Nome Completo: [____________]    ║
║  Usuário:       [____________]    ║
║  Senha:         [____________]    ║
║  Confirmar:     [____________]    ║
║                                   ║
║     [   CADASTRAR   ]             ║
║     [ JÁ TENHO CONTA ]            ║
╚═══════════════════════════════════╝
```

### 2. Tela de Login
```
╔═══════════════════════════════════╗
║         🔐 LOGIN                  ║
╠═══════════════════════════════════╣
║  Usuário: [__________________]    ║
║  Senha:   [__________________]    ║
║                                   ║
║     [     ENTRAR     ]            ║
║     [    CADASTRAR   ]            ║
╚═══════════════════════════════════╝
```

### 3. Tela de Gerenciamento
```
╔═══════════════════════════════════╗
║    🏪 GERENCIAMENTO PDV           ║
╠═══════════════════════════════════╣
║  [ Gerenciar Pessoas      ]       ║
║  [ Gerenciar Preços       ]       ║
║  [ Gerenciar Produtos     ]       ║
║  [ Gerenciar Custos       ]       ║
║  [ Gerenciar Estoques     ]       ║
║  [ Gerenciar Acessos      ]       ║
║  [ Gerenciar Contatos     ]       ║
╚═══════════════════════════════════╝
```

---

## ⚙️ CONFIGURAÇÃO DA IDE

### IntelliJ IDEA - Criar Configuração de Execução

1. **Run → Edit Configurations**
2. **+ (Add New Configuration) → Application**
3. Preencha:
   - **Name:** `PDV System`
   - **Main class:** `br.com.PdvFrontEnd.view.MainApp`
   - **Working directory:** `$MODULE_WORKING_DIR$`
   - **Use classpath of module:** `pdv-frontend`
4. **Apply → OK**

Agora você pode executar clicando no ícone ▶️ na barra superior!

---

## 🐛 TROUBLESHOOTING

### Problema: Erro de compilação
**Solução:**
```powershell
mvn clean compile
```

### Problema: Tela não abre
**Solução:**
- Verifique se tem Java 17+ instalado
- Verifique se o projeto está compilado
- Tente executar pelo Maven primeiro

### Problema: Esqueci a senha
**Solução:**
```powershell
cd C:\Users\Aluno\Documents\pdv-posto-combustivel-main\JavaPoo-Front-End-main
Remove-Item user_config.properties
```
Depois execute novamente e faça novo cadastro.

---

## 📍 LOCALIZAÇÃO DOS ARQUIVOS

```
JavaPoo-Front-End-main/
├── src/
│   └── main/
│       └── java/
│           └── br/
│               └── com/
│                   └── PdvFrontEnd/
│                       ├── view/
│                       │   ├── MainApp.java ← EXECUTE ESTE!
│                       │   ├── LoginView.java
│                       │   └── RegisterView.java
│                       └── util/
│                           └── SessionManager.java
└── user_config.properties ← Credenciais salvas aqui
```

---

## ✅ CHECKLIST

Antes de executar, verifique:

- [ ] Projeto compilado (`mvn compile`)
- [ ] Java 17+ instalado
- [ ] IDE configurada (IntelliJ ou Eclipse)
- [ ] Backend rodando (se for usar as funcionalidades de CRUD)

---

## 🎯 RESUMO

**Para executar o sistema:**

1. Abra a IDE (IntelliJ ou Eclipse)
2. Abra o projeto
3. Navegue até: `src/main/java/br/com/PdvFrontEnd/view/MainApp.java`
4. Clique com botão direito → Run 'MainApp.main()'
5. Pronto! 🎉

**Primeira vez:** Cadastro → Login → Gerenciamento
**Próximas vezes:** Login → Gerenciamento

---

## 🎊 PRONTO PARA USAR!

Agora é só executar o **MainApp.java** pela sua IDE e o sistema iniciará automaticamente pelo login!

**Sem arquivos .bat necessários!** ✅

---

*Guia atualizado em 30/10/2025*
*Execução direta pela IDE - Simples e fácil!*

