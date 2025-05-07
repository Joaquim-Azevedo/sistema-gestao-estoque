package com.joaquim.sistemagestaoestoque.service;

import com.joaquim.sistemagestaoestoque.ConnectionFactory;
import com.joaquim.sistemagestaoestoque.dao.EstoqueDAO;
import com.joaquim.sistemagestaoestoque.dao.MovimentacaoDAO;
import com.joaquim.sistemagestaoestoque.model.*;

import java.sql.Connection;
import java.util.*;

public class Estoque {
    private ConnectionFactory connection;
    private Connection conn;

    public Estoque() {
        this.connection = new ConnectionFactory();
        this.conn = connection.getConnection();
    }

    // Adicionar produtos do estoque
    public void adicionarProduto(String nome, String descricao, Categoria categoria, double valorUnidade){
        new EstoqueDAO(conn).adicionarProduto(nome, descricao, categoria, valorUnidade);
    }

    // Remover produtos do estoque - não recomendado.
    public void removerProduto(int id){
        new EstoqueDAO(conn).removerProduto(id);
    }

    // Fornecer um produto
    public void fornecerProduto(int id, int quantidade) {
        new MovimentacaoDAO(conn).movimentarProduto(Tipo.ENTRADA, id, quantidade);
        new EstoqueDAO(conn).fornecerProduto(id, quantidade);
    }

    // Executar a saída / venda de um produto.
    public void saidaProduto(int id, int quantidade) {
        new MovimentacaoDAO(conn).movimentarProduto(Tipo.SAIDA, id, quantidade);
        new EstoqueDAO(conn).saidaProduto(id, quantidade);
    }

    public void adicionarFornecedor(String nome, String cnpj, String contato){
        new EstoqueDAO(conn).adicionarFornecedor(nome, cnpj, contato);
    }

    // Remover um fornecedor do estoque.
    public void removerFornecedor(String cnpj){
        new EstoqueDAO(conn).removerFornecedor(cnpj);
    }

    public List<Produto> listarProdutos(){
        return new EstoqueDAO(conn).listarProdutos();
    }

    public Produto buscarProdutoId(int id){
        return new EstoqueDAO(conn).buscarProdutoId(id);
    }

    public List<Movimentacao> listarMovimentacoesId(int tipoLista, int id, Tipo tipo) {
        return new MovimentacaoDAO(conn).listarMovimentacaoTipo(tipoLista, id, tipo);
    }

}
