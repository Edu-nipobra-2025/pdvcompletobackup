# Guia para Enviar o Projeto para o GitHub

## 📦 Estrutura do Repositório

Este projeto contém **dois subprojetos** integrados:
- **Backend**: Spring Boot + PostgreSQL
- **Frontend**: Swing Desktop Application

Ambos estão no mesmo repositório e podem ser executados juntos no IntelliJ IDEA.

## 🚀 Como Enviar para o GitHub

### 1. Inicialize o repositório Git (se ainda não fez)

```bash
cd "C:\Users\sidney\Documents\faculdades 2025\pdvpostocombustivel"
git init
git add .
git commit -m "Projeto PDV Posto Combustível - Backend + Frontend integrados"
```

### 2. Crie um repositório no GitHub

1. Acesse: https://github.com/new
2. Nome: `pdv-posto-combustivel`
3. Descrição: `Sistema PDV para Posto de Combustível - Java Spring Boot + Swing`
4. Deixe **público** ou **privado** (sua escolha)
5. **NÃO** marque "Initialize with README" (já temos um)
6. Clique em **Create repository**

### 3. Conecte e envie para o GitHub

```bash
git remote add origin https://github.com/SEU_USUARIO/pdv-posto-combustivel.git
git branch -M main
git push -u origin main
```

## 📝 Estrutura que será enviada

```
pdv-posto-combustivel/          (Repositório principal)
├── README.md                   (Documentação principal)
├── .gitignore                  (Arquivos a ignorar)
├── pdv-posto-combustivel/      (Subprojeto Backend)
│   ├── src/
│   ├── pom.xml
│   └── README.md
├── JavaPoo-Front-End-main/     (Subprojeto Frontend)
│   ├── src/
│   ├── pom.xml
│   └── README.md
└── _docs_e_scripts/            (Documentação e scripts)
    ├── COMO_USAR_SIMPLES.md
    ├── EXECUTAR_BACKEND.bat
    ├── EXECUTAR_FRONTEND.bat
    └── ... (outros arquivos)
```

## ✅ Benefícios dessa estrutura

1. ✅ **Tudo em um só lugar**: Backend + Frontend no mesmo repositório
2. ✅ **Fácil de clonar**: Outras pessoas baixam tudo de uma vez
3. ✅ **Documentação organizada**: Pasta `_docs_e_scripts` com tudo
4. ✅ **Execução simples**: Configurações prontas no IntelliJ
5. ✅ **Scripts auxiliares**: Arquivos .bat para quem não usa IDE

## 🔄 Atualizações futuras

Sempre que fizer alterações:

```bash
git add .
git commit -m "Descrição da alteração"
git push origin main
```

## 👥 Para outras pessoas usarem

Quando alguém quiser usar seu projeto:

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/SEU_USUARIO/pdv-posto-combustivel.git
   ```

2. **Abra no IntelliJ** a pasta raiz `pdv-posto-combustivel`

3. **Configure o PostgreSQL** (criar banco com os scripts)

4. **Execute** usando as configurações de execução ou os scripts .bat

## 📌 Dicas

- ✅ O `.gitignore` já está configurado para **não** enviar:
  - Pasta `target/` (arquivos compilados)
  - Configurações locais do IDE
  - Arquivos temporários

- ✅ Mantenha a estrutura atual - ela está **organizada e profissional**

- ✅ A pasta `_docs_e_scripts` é **muito útil** para quem vai usar seu projeto

## 🌟 Melhorias opcionais

Se quiser deixar o repositório ainda mais profissional:

1. **Adicione badges** no README.md:
   - Java version
   - Spring Boot version
   - License

2. **Adicione screenshots** da aplicação rodando

3. **Crie uma pasta `docs/`** separada se quiser documentação mais extensa

4. **Configure GitHub Actions** para CI/CD (mais avançado)

