# 🗄️ GUIA DE CONFIGURAÇÃO DE BANCO DE DADOS

## 📋 O que foi configurado?

O sistema agora suporta **2 perfis de banco de dados**:

### 1️⃣ Perfil H2 (Banco em memória - para visualização rápida)
- ✅ H2 Console habilitado
- ✅ Dados em memória (reinicia quando para o servidor)
- ✅ Fácil de visualizar dados pelo navegador

### 2️⃣ Perfil PostgreSQL (Banco persistente - produção)
- ✅ Dados salvos permanentemente
- ✅ Melhor performance
- ✅ Visualização via pgAdmin

---

## 🚀 Como usar?

### **Para usar H2 (com H2 Console):**

1. **Edite o arquivo:** `application.properties`
2. **Mude a linha:**
   ```properties
   spring.profiles.active=h2
   ```

3. **Execute o backend:**
   ```bash
   cd pdv-posto-combustivel
   mvn clean spring-boot:run
   ```

4. **Acesse o H2 Console:**
   - URL: http://localhost:8080/h2-console
   - JDBC URL: `jdbc:h2:mem:pdvpostocombustivel`
   - Username: `sa`
   - Password: (deixe em branco)

---

### **Para usar PostgreSQL:**

1. **Edite o arquivo:** `application.properties`
2. **Mude a linha:**
   ```properties
   spring.profiles.active=postgres
   ```

3. **Execute o backend:**
   ```bash
   cd pdv-posto-combustivel
   mvn clean spring-boot:run
   ```

4. **Visualize os dados:**
   - Use o **pgAdmin**
   - OU execute consultas SQL direto no PostgreSQL

---

## 🔍 Como verificar se está funcionando?

### **Teste rápido:**

1. **Inicie o backend** (na pasta `pdv-posto-combustivel`)
2. **Inicie o frontend** (na pasta `JavaPoo-Front-End-main`)
3. **Adicione uma pessoa ou contato**
4. **No console do backend**, você deve ver:
   ```
   Hibernate: insert into pessoas (...) values (...)
   ```

5. **Consulte os dados:**
   - **Se usando H2**: http://localhost:8080/h2-console
   - **Se usando PostgreSQL**: pgAdmin

---

## ⚠️ IMPORTANTE

### **Diferenças entre H2 e PostgreSQL:**

| Aspecto | H2 | PostgreSQL |
|---------|----|-----------| 
| **Persistência** | ❌ Dados perdidos ao reiniciar | ✅ Dados permanentes |
| **Visualização** | ✅ H2 Console (navegador) | 🔧 pgAdmin |
| **Performance** | ⚡ Mais rápido para testes | 🚀 Melhor para produção |
| **Uso** | 🧪 Desenvolvimento/Testes | 🏭 Produção |

---

## 🐛 Solução de Problemas

### **"Não vejo dados no H2 Console"**
- Verifique se `spring.profiles.active=h2` está configurado
- Reinicie o backend
- Use as credenciais corretas (sa / sem senha)

### **"Não vejo dados no PostgreSQL"**
- Verifique se `spring.profiles.active=postgres` está configurado
- Confirme que o PostgreSQL está rodando
- Use o pgAdmin para consultar

### **"Tabelas não são criadas"**
- Com H2: `ddl-auto=create-drop` cria automaticamente
- Com PostgreSQL: `ddl-auto=update` atualiza estrutura
- Veja os logs do console do backend

---

## 📂 Arquivos de Configuração

```
pdv-posto-combustivel/src/main/resources/
├── application.properties          (configuração principal)
├── application-h2.properties       (configuração H2)
└── application-postgres.properties (configuração PostgreSQL)
```

---

## 🎯 Recomendação

**Para desenvolvimento e visualização rápida:**
```properties
spring.profiles.active=h2
```

**Para testes com dados persistentes:**
```properties
spring.profiles.active=postgres
```

