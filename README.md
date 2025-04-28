# 🏷️ Sistema de Gestão de Estoque

Projeto em **Java 24**, voltado para o controle e gerenciamento de estoque, permitindo cadastro de produtos, fornecedores, movimentações e geração de relatórios simples.

## ✨ Funcionalidades

- 📦 Cadastro e listagem de **Produtos** (com categorias e preço)
- 🏭 Cadastro e consulta de **Fornecedores**
- ➕ Reposição (entrada) de produtos no estoque
- ➖ Saída de produtos do estoque
- 📋 Registro e consulta de movimentações (entrada/saída)
- 🗂️ Relatórios de histórico de movimentações

## 🗂️ Estrutura básica do projeto

- `Produto`: Representação de um produto.
- `Fornecedor`: Entidade/empresa que representa quem fornece os produtos.
- `Movimentacao`: Classe para registrar as entradas e saídas do estoque.
- `Estoque`: Classe de serviço para manipulação do estoque.
- `Enums`: Categorias de produtos e tipos de movimentação

## 🚀 Como executar

1. Clone este repositório:
    ```sh
    git clone https://github.com/seu-usuario/seu-repositorio.git
    ```
2. Abra no IntelliJ IDEA ou em outra IDE de sua preferência
3. Certifique-se de estar usando o Java 24
4. Execute a classe principal conforme o ponto de entrada do projeto

---

## 📝 Métodos e comandos disponíveis

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
- `imprimirMovimentacoes()`
  - Imprime o histórico de movimentações de entrada e saída do estoque.

### Consulta
- `getProdutos()`
  - Retorna a lista de produtos cadastrados no estoque.

---

## 👨‍💻 Exemplo de uso (pseudocódigo)

---
java Estoque estoque = new Estoque();
// `Criar um fornecedor:` Fornecedor fornecedor = new Fornecedor("Empresa LTDA", "129391210192", "empresa@email.com");
// `Adicionar produtos:` estoque.adicionarProduto("Arroz Branco", "Arroz Branco", Categoria.ALIMENTO, 12.90); estoque.
// `Remover produto por id:` estoque.removerProduto(1);
adicionarProduto("Mouse Logitech G903", "Mouse Logitech G903 Preto Sem Fio", Categoria.ELETRONICO, 550);
// `Registrar fornecimento de produtos (entrada de estoque):` estoque.fornecerProduto(fornecedor, 1, 300); estoque.
fornecerProduto
(fornecedor, 2, 20);
// `Registrar saída de produto:` estoque.saidaProduto(fornecedor, 2, 5);
// `Listar todos os produtos do estoque:` System.out.println(estoque);
// `Imprimir histórico de movimentações:` estoque.imprimirMovimentacoes();

Adapte esses métodos conforme a interface de uso (console, interface gráfica etc).

---

## 📈 Futuras implementações

- 💾 Persistência de dados via banco de dados (MySQL, PostgreSQL) ou arquivo local
- 🔎 Filtros avançados de busca por produtos, fornecedores e movimentações
- 👤 Cadastro e autenticação de usuários com permissões
- 🚨 Alertas automáticos de baixo estoque
- ✅ Melhor validação dos dados de entrada

---

Projeto criado para fins de aprendizagem e como base para sistemas mais completos de controle de estoque.  
Contribuições são muito bem-vindas! 🚀
