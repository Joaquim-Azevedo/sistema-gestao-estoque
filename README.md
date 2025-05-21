# 🏷️ Sistema de Gestão de Estoque

Projeto em **Java 24**, voltado para o controle e gerenciamento de estoque, permitindo cadastro de produtos, fornecedores, movimentações e geração de relatórios simples.
Agora integrado quase que totalmente com banco de dados!

## Funcionalidades

-  Cadastro e listagem de **Produtos** (com categorias e preço)
-  Cadastro e consulta de **Fornecedores**
-  Reposição (entrada) de produtos no estoque
-  Saída de produtos do estoque
-  Registro e consulta de movimentações (entrada/saída)
-  Relatórios de histórico de movimentações

##  Estrutura básica do projeto

- `Produto`: Representação de um produto.
- `Fornecedor`: Entidade/empresa que representa quem fornece os produtos.
- `Movimentacao`: Classe para registrar as entradas e saídas do estoque.
- `Estoque`: Classe de serviço para manipulação do estoque.
- `Enums`: Categorias de produtos e tipos de movimentação

## 🚀 Como executar e usar

### 1. Pré-requisitos

- [Java 24](https://www.oracle.com/br/java/technologies/downloads/)
- [MySQL Server](https://dev.mysql.com/downloads/mysql/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/) ou outra IDE com suporte para Java.
- Driver JDBC MySQL ([Veja como baixar!](https://dev.mysql.com/downloads/connector/j/))

### 2. Clone o projeto

Clone o repositório

 ```sh git clone https://github.com/Joaquim-Azevedo/sistema-gestao-estoque```

### 3. Configure o banco de dados

O sistema está pronto para conectar em um banco MySQL rodando localmente, conforme abaixo:

- **Host:** `localhost`
- **Porta:** `3306`
- **Banco:** `estoquedb`
- **Usuário:** `root`
- **Senha:** `manager`

> **Importante:**  
> Modifique as configurações de acesso no arquivo `ConnectionFactory.java` se desejar mudar usuário, senha ou nome do banco.

Crie o banco de dados e tabelas:

- **Meus dumps do SQL para te ajudar ->** [Script SQL EstoqueDB](https://drive.google.com/file/d/1wW9AcE1iB_PHZRkxskP6F6xOQ_fzWaAq/view?usp=sharing)

### 4. Execute o projeto

Execute a classe principal (ex: `EstoqueApplication.java`) e siga o menu interativo pelo console para utilizar as opções.

---

## Métodos e comandos disponíveis

Abaixo estão os métodos/comandos reais disponíveis no sistema para gerenciamento de estoque:

### Produtos
- `adicionarProduto(String nome, String descricao, Categoria categoria, double valorUnidade)`
  - Adiciona um novo produto no estoque.

- `removerProduto(int id)`
  - Remove um produto do estoque, a partir do identificador.

### Fornecimento e movimentação de estoque
- `fornecerProduto(Fornecedor fornecedor, int id, int quantidade)`
  - Registra o fornecimento (entrada) de um produto por um fornecedor.

- `saidaProduto(Fornecedor fornecedor, int id, int quantidade)`
  - Registra a saída de um produto do estoque.

### Movimentações
- `listarMovimentacoes(int tipo, int id, Tipo tipoMovimentacao)`
  - 3 tipos de listagem de movimentações
  - Imprime o histórico de movimentações de entrada e saída do estoque.

### Consulta
- `listarProdutos()`
  - Retorna a lista de produtos cadastrados no estoque.

---

# 🛠️ FAQ e dicas

- Caso precise mudar o usuário, senha ou porta do banco, altere a linha de conexão em `ConnectionFactory.java`:

  ```java
  // Exemplo:
  DriverManager.getConnection("jdbc:mysql://localhost:3306/estoquedb?user=usuario_novo&password=senha_nova");
  
  
- Se aparecer erro de driver ("No suitable driver!"), verifique se o JAR do MySQL Connector está configurado nas dependências do projeto.

---

## Exemplos de uso (console)

- **Iniciar sistema:**  
  Execute o projeto e navegue pelas opções do menu no terminal.

- **Buscar produto por ID**
  Informe o ID do produto para realizar a busca.
- **Adicionar produto:**  
  Siga o menu para informar nome, descrição, categoria e valor.

- **Registrar entrada/saída:**  
  Informe o ID do produto e a quantidade desejada.

- **Adicionar/remover fornecedor:**  
  Forneça nome, CNPJ e contato.

- **Listar movimentações:**
  Forneça o tipo de lista e/ou o ID do produto e/ou o tipo de movimentação.

Todas as operações são persistidas automaticamente no banco de dados configurado.

---

## Exemplo de uso (pseudocódigo)

java Estoque estoque = new Estoque();
- `Criar um fornecedor:` Fornecedor fornecedor = new Fornecedor("Empresa LTDA", "129391210192", "empresa@email.com");
- `Adicionar produtos:` estoque.adicionarProduto("Arroz Branco", "Arroz Branco", Categoria.ALIMENTO, 12.90);
- `Remover produto por id:` estoque.removerProduto(1);
- `Registrar fornecimento de produtos (entrada de estoque):` estoque.fornecerProduto(fornecedor, 1, 300);
- `Registrar saída de produto:` estoque.saidaProduto(fornecedor, 2, 5);
- `Listar todos os produtos do estoque:` estoque.listarProdutos();
- `Imprimir histórico de movimentações:` estoque.imprimirMovimentacoes();

Adapte esses métodos conforme a interface de uso (console, interface gráfica etc).

---

## 📈 Futuras implementações

-  Persistência de dados via banco de dados (MySQL, PostgreSQL) ou arquivo local ✅
-  Interface via console para uso mais intuitivo ✅
-  Filtros avançados de busca por produtos ✅ 
-  Geração de relatórios em PDF ou Excel
-  API REST para integração com outros sistemas

---

## Implementações em desenvolvimento  
- Listagem das movimentações (persistindo dados no banco de dados) ✅
- Filtros de busca mais avançados para produtos, fornecedores e movimentações (por nome, por categoria)
- Alertas automáticos de baixo estoque
- Melhor validação dos dados de entrada


---

## 💡 Sugestões e dúvidas

Abra issues no repositório ou colabore via Pull Requests!

---

Projeto criado para fins de aprendizagem e como base para sistemas mais completos de controle de estoque.  
Contribuições são muito bem-vindas! 🚀
