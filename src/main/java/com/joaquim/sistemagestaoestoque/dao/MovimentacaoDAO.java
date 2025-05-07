package com.joaquim.sistemagestaoestoque.dao;

import com.joaquim.sistemagestaoestoque.model.Movimentacao;
import com.joaquim.sistemagestaoestoque.model.Tipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovimentacaoDAO {
    private Connection conn;

    public MovimentacaoDAO(Connection conn) {
        this.conn = conn;
    }

    public void movimentarProduto(Tipo tipo, int id, int quantidade){
        String selectSql = "SELECT quantidade, preco_unidade FROM produto WHERE produto_id = ?";
        String insertSql = "INSERT INTO movimentacao (produto_id, quantidade, valor_total, tipo) " +
                "VALUES (?, ?, ?, ?)";

        try(PreparedStatement selectSmt = conn.prepareStatement(selectSql)){
            selectSmt.setInt(1, id);

            try (ResultSet rs = selectSmt.executeQuery()) {
                if(rs.next()){
                    // Calculo do preco total da movimentação
                    int getQuantidade = rs.getInt("quantidade");
                    double preco_unidade = rs.getDouble("preco_unidade");
                    double precoTotal = quantidade * preco_unidade;

                    try (PreparedStatement insertSmt = conn.prepareStatement(insertSql)) {
                        insertSmt.setInt(1, id);
                        insertSmt.setInt(2, quantidade);
                        insertSmt.setDouble(3, precoTotal);
                        if(tipo == Tipo.ENTRADA) {
                            insertSmt.setString(4, tipo.name());
                        } else {
                            insertSmt.setString(4, tipo.name());
                        }
                        insertSmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String resgatarNome(int id) {
        String nomeSelectSql = "SELECT nome FROM produto WHERE produto_id = ?";

        String nome = null;
        try (PreparedStatement selectSmt = conn.prepareStatement(nomeSelectSql)) {
            selectSmt.setInt(1, id);
            try (ResultSet getNomeRs = selectSmt.executeQuery()) {
                if (getNomeRs.next()) {
                    nome = getNomeRs.getString("nome");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return nome;
    }

    public List<Movimentacao> listarMovimentacaoTipo(int tipoLista, int id, Tipo tipoMovimentacao){
        List<Movimentacao> movimentacoes = new ArrayList<>();
        String movimentacaoSelectSql = null;

        if(tipoLista == 1) {
            movimentacaoSelectSql = "SELECT * FROM movimentacao ORDER BY produto_id ASC";
        } else if (tipoLista == 2) {
            movimentacaoSelectSql = "SELECT * FROM movimentacao WHERE produto_id = ? ORDER BY produto_id ASC";
        } else if (tipoLista == 3) {
            movimentacaoSelectSql = "SELECT * FROM movimentacao WHERE tipo = ? ORDER BY produto_id ASC";
        }

        try(PreparedStatement movimentacaoSmt = conn.prepareStatement(movimentacaoSelectSql)) {
            if (tipoLista == 2) {
                movimentacaoSmt.setInt(1, id);
            } else if (tipoLista == 3) {
                movimentacaoSmt.setString(1, tipoMovimentacao.name());
            }
            try (ResultSet movimentacaoRs = movimentacaoSmt.executeQuery()) {
                while(movimentacaoRs.next()){
                    int produto_id = movimentacaoRs.getInt(1);
                    int quantidade = movimentacaoRs.getInt(2);
                    double valor_total = movimentacaoRs.getDouble(3);
                    Tipo tipo = Tipo.valueOf(movimentacaoRs.getString(4));
                    String data_hora = movimentacaoRs.getString(5);

                    String nome = resgatarNome(produto_id);
                    Movimentacao movimentacao = new Movimentacao(nome, produto_id, quantidade, valor_total, tipo, data_hora);
                    movimentacoes.add(movimentacao);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return movimentacoes;
    }


}
