package com.joaquim.sistemagestaoestoque.tests;

import com.joaquim.sistemagestaoestoque.model.Categoria;
import com.joaquim.sistemagestaoestoque.model.Produto;
import com.joaquim.sistemagestaoestoque.service.Estoque;

import java.sql.SQLOutput;
import java.util.Locale;
import java.util.Scanner;

public class EstoqueTest02 {
    private static Estoque etq = new Estoque();
    private static Scanner sc = new Scanner(System.in).useLocale(Locale.US);

    public static void main(String[] args) {

        int opcao = menu();

        while (opcao != 7){
            System.out.println("Digite uma opção: ");

            switch (opcao) {
                case 1:
                    listarProdutos();
                    break;
                case 2:
                    atualizarProduto();
                    break;
                case 3:
                    registrarMovProduto();
                    break;
                case 4:
                    buscarProdutoId();
                    break;
                case 5:
                    atualizarFornecedor();
                    break;
            }

            opcao = menu();
        }

        sc.close();
    }

    private static int menu(){
        System.out.println("""
                ---- Menu Estoque ----
                1. Listar todos os produtos
                2. Adicionar/remover um produto
                3. Registrar movimentação de um produto (ID)
                4. Buscar produto por ID
                5. Adicionar/remover fornecedor
                """);
        return sc.nextInt();
    }

    private static void listarProdutos(){
        System.out.println("Produtos cadastrados: ");
        for (Produto produto : etq.listarProdutos()) {
            System.out.println(produto);
        }
    }

    private static void atualizarProduto(){
        System.out.println("1. Adicionar");
        System.out.println("2. Remover");
        int opcao = sc.nextInt();

        if (opcao == 1) {
            System.out.println("Você escolheu adicionar um produto");
            System.out.println("Digite o nome do produto: ");
            String nome = sc.nextLine();

            System.out.println("Digite a descrição do produto: ");
            String descricao = sc.nextLine();

            System.out.println("Digite a categoria do produto: ");
            Categoria categoria = Categoria.valueOf(sc.next());

            System.out.println("Digite o valor unitário do produto: ");
            double valorUnitario = sc.nextDouble();

            etq.adicionarProduto(nome, descricao, categoria, valorUnitario);
            System.out.println("Produto adicionado com sucesso!");
        } else if (opcao == 2) {
            System.out.println("Você escolheu remover um produto");
            System.out.println("Lista de produtos: ");
            System.out.println(etq.listarProdutos());

            System.out.println("Digite o ID do produto que deseja remover: ");
            int produto_id = sc.nextInt();

            System.out.println("Tem certeza? Esta ação é irreversível. (S/N)");
            String validacao = sc.next();

            if (validacao.equalsIgnoreCase("n")) {
                System.out.println("Nenhum produto será excluído");
                return;
            }
            etq.removerProduto(produto_id);
            System.out.println("Produto removido com sucesso!");
        }

        sc.next();
    }

    private static void registrarMovProduto() {
        System.out.println("1. Entrada de um produto");
        System.out.println("2. Saída de um produto");
        int opcao = sc.nextInt();

        if (opcao == 1) {
            System.out.println("Você escolheu registrar a entrada de um produto");
            System.out.println(etq.listarProdutos());
            System.out.println("Digite o ID do produto: ");
            int produto_id = sc.nextInt();

            System.out.println("Digite a quantidade: ");
            int quantidade = sc.nextInt();

            etq.fornecerProduto(produto_id, quantidade);
            System.out.println("Movimentação de ENTRADA realizada com sucesso!");
        } else if (opcao == 2) {
            System.out.println("Você escolheu registrar a saída de produto");
            System.out.println(etq.listarProdutos());
            System.out.println("Digite o ID do produto: ");
            int produto_id = sc.nextInt();

            System.out.println("Digite a quantidade: ");
            int quantidade = sc.nextInt();

            etq.saidaProduto(produto_id, quantidade);
            System.out.println("Movimentação de SAÍDA realizada com sucesso!");
        }

        sc.next();
    }

    private static void buscarProdutoId(){
        System.out.println("Digite o ID do produto que deseja buscar: ");
        int produto_id = sc.nextInt();

        etq.buscarProdutoId(produto_id);
        if (etq.buscarProdutoId(produto_id) == null) {
            System.out.println("Produto inexistente ou não encontrado.");
        }
    }

    private static void atualizarFornecedor(){
        System.out.println("1. Adicionar fornecedor");
        System.out.println("2. Remover fornecedor");

        int opcao = sc.nextInt();

        if(opcao == 1) {
            System.out.println("Você escolheu adicionar um fornecedor");
            System.out.println("Digite o nome do fornecedor/empresa: ");
            String nome = sc.nextLine();

            System.out.println("Digite o CNPJ do fornecedor/empresa: ");
            String cnpj = sc.nextLine();

            System.out.println("Digite o contato do fornecedor/empresa: ");
            String contato = sc.nextLine();

            etq.adicionarFornecedor(nome, cnpj, contato);
            System.out.println("Fornecedor adicionado com sucesso!");
        } else if (opcao == 2) {
            System.out.println("Você escolheu remover um fornecedor");

            System.out.println("Digite o CNPJ do fornecedor/empresa: ");
            String cnpj = sc.nextLine();

            etq.removerFornecedor(cnpj);
            System.out.println("Fornecedor removido com sucesso!");
        }
    }

}
