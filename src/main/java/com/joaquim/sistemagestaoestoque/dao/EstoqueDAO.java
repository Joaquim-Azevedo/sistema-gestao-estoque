package com.joaquim.sistemagestaoestoque.dao;

import com.joaquim.sistemagestaoestoque.model.Categoria;
import com.joaquim.sistemagestaoestoque.model.Movimentacao;
import com.joaquim.sistemagestaoestoque.model.Produto;
import com.joaquim.sistemagestaoestoque.model.Tipo;

import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EstoqueDAO {
    private Connection conn;
    private List<Movimentacao> movimentacoes;

    public EstoqueDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean produtoExiste(String nome, Categoria categoria) {
        String sql = "SELECT COUNT(*) FROM produto WHERE LOWER(nome) = ? AND categoria = ?";
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, nome.toLowerCase());
            pstm.setString(2, categoria.name());

            // Resgatando os valores e retornando um boolean falso se não existe.
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Se algum produto já existe, retorna true
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public Produto adicionarProduto(String nome, String descricao, Categoria categoria, double valorUnidade){
        if(produtoExiste(nome, categoria)){
            System.out.println("Produto com nome e categoria já cadastrados");
            return null;
        }

        Produto novoProduto = new Produto(nome, descricao, categoria, valorUnidade);

        String sql = "INSERT INTO produto (nome, descricao, categoria, quantidade, preco_unidade, " +
                "preco_total)" + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, novoProduto.getNome());
            pstm.setString(2, novoProduto.getDescricao());
            pstm.setString(3, novoProduto.getCategoria().name());
            pstm.setInt(4, novoProduto.getQuantidade());
            pstm.setDouble(5, novoProduto.getPrecoUnidade());
            pstm.setDouble(6, novoProduto.getPrecoTotal());
            pstm.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return novoProduto;
    }

    public void removerProduto(int id){
        String sql = "DELETE FROM produto WHERE produto_id = ?";

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, id);
            pstm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void fornecerProduto(int id, int quantidade){
        String selectSql = "SELECT quantidade, preco_unidade FROM produto WHERE produto_id = ?";
        String updateSql = "UPDATE produto SET quantidade = ?, preco_total = ? WHERE produto_id = ?";

        try (PreparedStatement selectSmt = conn.prepareStatement(selectSql)){

            selectSmt.setInt(1, id);

            try (ResultSet rs = selectSmt.executeQuery()){
                if(rs.next()){
                    int quantidadeAtual = rs.getInt("quantidade");
                    double precoUnidade = rs.getDouble("preco_unidade");
                    int novaQuantidade = quantidadeAtual + quantidade;
                    double novoPrecoTotal = novaQuantidade * precoUnidade;

                    try (PreparedStatement updateSmt = conn.prepareStatement(updateSql)){
                        updateSmt.setInt(1, novaQuantidade);
                        updateSmt.setDouble(2, novoPrecoTotal);
                        updateSmt.setInt(3, id);
                        updateSmt.executeUpdate();
                        System.out.println("Quantidade atualizada para: " + novaQuantidade);
                    }
                } else {
                    System.out.println("Produto não encontrado.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saidaProduto(int id, int quantidade){
        String selectSql = "SELECT quantidade, preco_unidade FROM produto WHERE produto_id = ?";
        String updateSql = "UPDATE produto SET quantidade = ?, preco_total = ? WHERE produto_id = ?";


        try (PreparedStatement selectSmt = conn.prepareStatement(selectSql)) {
            selectSmt.setInt(1, id);

            try (ResultSet rs = selectSmt.executeQuery()){
                if(rs.next()){
                    int quantidadeAtual = rs.getInt("quantidade");
                    double precoUnidade = rs.getDouble("preco_unidade");
                    int novaQuantidade = quantidadeAtual - quantidade;
                    double novoPrecoTotal = novaQuantidade * precoUnidade;

                    try (PreparedStatement updateSmt = conn.prepareStatement(updateSql)){
                        updateSmt.setInt(1, novaQuantidade);
                        updateSmt.setDouble(2, novoPrecoTotal);
                        updateSmt.setInt(3, id);
                        updateSmt.executeUpdate();
                        System.out.println("Quantidade atualizada para: " + novaQuantidade);
                    }
                } else {
                    System.out.println("Produto não encontrado.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void adicionarFornecedor(String nome, String cnpj, String contato){
        String sql = "INSERT INTO fornecedor (nome, cnpj, contato) VALUES (?, ?, ?)";

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, nome);
            pstm.setString(2, cnpj);
            pstm.setString(3, contato);
            pstm.execute();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void removerFornecedor(String cnpj){
        String sql = "DELETE FROM fornecedor WHERE cnpj = ?";

        try(PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, cnpj);
            pstm.execute();

            System.out.println("Fornecedor de id " + cnpj + " removido com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Produto> listarProdutos(){
        Set<Produto> produtos = new HashSet<>();

        String sql = "SELECT * FROM produto";

        try(PreparedStatement pstm = conn.prepareStatement(sql)) {
            try (ResultSet rs = pstm.executeQuery()) {
                while(rs.next()){
//                  String nome, String descricao, Categoria categoria, double precoUnidade

                    int produto_id = rs.getInt(1);
                    String nome = rs.getString(2);
                    String descricao = rs.getString(3);
                    Categoria categoria = Categoria.valueOf(rs.getString(4));
                    int quantidade = rs.getInt(5);
                    double precoUnidade = rs.getDouble(6);

                    Produto listProduto = new Produto(nome, descricao, categoria, precoUnidade);
                    listProduto.setQuantidade(quantidade);
                    listProduto.setPrecoTotal();

                    // Atribuindo o primary key ao ID do produto depois de criado/instanciado.
                    listProduto.setProduto_id(produto_id);

                    produtos.add(listProduto);

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return produtos;
    }

    public Produto buscarProdutoId(int id) {
        String sql = "SELECT * FROM produto WHERE produto_id = ?";

        Produto getProduto = null;
        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, id);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
//                  String nome, String descricao, Categoria categoria, double precoUnidade

                    int produto_id = rs.getInt(1);
                    String nome = rs.getString(2);
                    String descricao = rs.getString(3);
                    Categoria categoria = Categoria.valueOf(rs.getString(4));
                    int quantidade = rs.getInt(5);
                    double precoUnidade = rs.getDouble(6);

                    getProduto = new Produto(nome, descricao, categoria, precoUnidade);
                    getProduto.setQuantidade(quantidade);
                    getProduto.setPrecoTotal();

                    getProduto.setProduto_id(produto_id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return getProduto;
    }


}
