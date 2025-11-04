# CORREÇÃO DO CAMPO "FUNÇÃO" (ROLE) - PASSO A PASSO

## 🔴 PROBLEMA IDENTIFICADO
O campo "Função" (role) não estava sendo salvo no banco de dados porque:
1. A tabela `pessoas` não tinha a coluna `role`
2. A entidade `Pessoa` no backend não tinha o atributo `role`
3. Os DTOs (PessoaRequest e PessoaResponse) não tinham o campo `role`
4. O PessoaService não estava mapeando o campo `role`

## ✅ CORREÇÕES APLICADAS

### 1. Backend - Arquivos Modificados:
- ✅ `Pessoa.java` - Adicionado campo `role` na entidade
- ✅ `PessoaRequest.java` - Adicionado campo `role` no DTO de entrada
- ✅ `PessoaResponse.java` - Adicionado campo `role` no DTO de saída
- ✅ `PessoaService.java` - Atualizado para mapear o campo `role` em todos os métodos

### 2. Frontend - Arquivos Modificados:
- ✅ `PessoaList.java` - Já estava correto com as opções: FRENTISTA, USUÁRIO, ADMIN

## 📋 PASSOS PARA APLICAR A CORREÇÃO

### PASSO 1: Adicionar coluna no Banco de Dados
Execute este SQL no **pgAdmin**:

```sql
-- Adicionar coluna role na tabela pessoas
ALTER TABLE pessoas ADD COLUMN IF NOT EXISTS role VARCHAR(50);

-- Atualizar registros existentes com valor padrão 'USUÁRIO'
UPDATE pessoas SET role = 'USUÁRIO' WHERE role IS NULL;
```

**Como executar:**
1. Abra o pgAdmin
2. Conecte no banco `pdvpostocombustivel`
3. Clique com botão direito no banco → **Query Tool**
4. Cole o SQL acima e clique em **Execute** (F5)

### PASSO 2: Recompilar o Backend
Execute o arquivo: `RECOMPILAR_BACKEND_ROLE.bat`

Ou execute manualmente:
```bash
cd "C:\Users\sidney\Documents\faculdades 2025\pdvpostocombustivel\pdv-posto-combustivel"
mvn clean install -DskipTests
```

### PASSO 3: Reiniciar o Backend
1. Pare o backend se estiver rodando (Ctrl+C no terminal)
2. Execute novamente: `EXECUTAR_BACKEND.bat`

### PASSO 4: Testar
1. Abra o frontend (MainApp)
2. Adicione uma nova pessoa com função "FRENTISTA"
3. Clique em "Atualizar" para recarregar a tabela
4. Verifique se a função aparece corretamente

## 🎯 RESULTADO ESPERADO
Agora quando você cadastrar uma pessoa com:
- Função: FRENTISTA
- Função: USUÁRIO  
- Função: ADMIN

O valor será salvo corretamente no banco de dados e exibido na tabela!

## 📝 OBSERVAÇÕES
- As pessoas já cadastradas terão a função "USUÁRIO" por padrão
- Você pode editar essas pessoas para alterar a função
- O campo aceita: FRENTISTA, USUÁRIO ou ADMIN

## ❓ DÚVIDAS?
Se após seguir todos os passos ainda não funcionar:
1. Verifique se o backend está rodando sem erros
2. Verifique se a coluna `role` foi criada no banco (SELECT * FROM pessoas)
3. Verifique os logs do backend no terminal

