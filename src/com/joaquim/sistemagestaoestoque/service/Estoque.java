package com.joaquim.sistemagestaoestoque.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.joaquim.sistemagestaoestoque.model.*;

public class Estoque {
    private List<Produto> produtos;
    private List<Movimentacao> movimentacoes;

    public Estoque() {
        this.produtos = new ArrayList<>();
        this.movimentacoes = new ArrayList<>();
    }

    // Adicionar produtos do estoque
    public Produto adicionarProduto(String nome, String descricao, Categoria categoria, double valorUnidade){
        Produto novoProduto = new Produto(nome, descricao, categoria, valorUnidade);

        this.produtos.add(novoProduto);
        System.out.println("Produto adicionado ao estoque com sucesso !");

        return novoProduto;
    }

    // Remover produtos do estoque -----> Usar ITERADOR para remover qualquer produto em coleções de dados.
    public void removerProduto(int id){
        Iterator<Produto> itProduto = this.produtos.iterator();
        boolean removido = false;
        while(itProduto.hasNext()){
            Produto produto = itProduto.next();
            if(produto.getId() == id){
                itProduto.remove();
                System.out.println("Produto removido do estoque com sucesso!");
                removido = true;
                break;
            }
        }
        if(!removido){
            System.out.println("Produto inexistente ou não encontrado!");
        }
    }

    // Fornecer um produto
    public void fornecerProduto(Fornecedor fornecedor, int id, int quantidade) {
        boolean encontrado = false;
        for(Produto produto : this.produtos) {
            if(produto.getId() == id) {
                encontrado = true;
                produto.reporProduto(quantidade);
                System.out.println("Fornecedor: " + fornecedor.getNome() +
                        " forneceu -> " + produto.getNome() + "." +
                        "\nQuantidade: " + quantidade +
                        "\nCategoria: " + produto.getCategoria());
                movimentacoes.add(new Movimentacao(produto, Tipo.ENTRADA, quantidade));
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Produto inexistente ou não encontrado!");
        }
    }

    // Executar a saída / venda de um produto.
    public void saidaProduto(Fornecedor fornecedor, int id, int quantidade) {
        boolean encontrado = false;
        for(Produto produto : this.produtos) {
            if(produto.getId() == id) {
                encontrado = true;
                produto.disporProduto(quantidade);
                System.out.println("Saída de produto "+
                        "\nProduto -> " + produto.getNome() + "." +
                        "\nQuantidade: " + quantidade +
                        "\nCategoria: " + produto.getCategoria());
                movimentacoes.add(new Movimentacao(produto, Tipo.SAIDA, quantidade));
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Produto inexistente ou não encontrado!");
        }
    }

    public void imprimirMovimentacoes(){
        for (Movimentacao movimentacao : movimentacoes){
            System.out.println("----- Movimentações -----");
            System.out.println("Produto: " + movimentacao.getProduto().getNome());
            System.out.println("ID: " + movimentacao.getProduto().getId());
            System.out.println("Tipo: " + movimentacao.getTipo());
            System.out.println("Quantidade: " + movimentacao.getQuantidade());
            System.out.println("Categoria: " + movimentacao.getProduto().getCategoria());
            System.out.println();
        }
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    @Override
    public String toString() {
        return "Estoque{" +
                "produtos=" + produtos +
                "}";
    }
}
