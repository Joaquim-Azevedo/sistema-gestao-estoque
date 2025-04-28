package com.joaquim.sistemagestaoestoque.model;

public final class Produto {
    private String nome;
    private String descricao;
    private int id;
    private static int proximoId = 0;
    private Categoria categoria;
    private int quantidade;
    private double precoUnidade;
    private double precoTotal;

    public Produto(String nome, String descricao, Categoria categoria, double precoUnidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.id = ++proximoId;
        this.categoria = categoria;
        this.quantidade = 0;
        this.precoUnidade = precoUnidade;
        this.setPrecoTotal();
    }

    public void reporProduto(int quantia){
        this.quantidade += quantia;
        this.setPrecoTotal();
    }

    public void disporProduto(int quantidade){
        this.quantidade -= quantidade;
        this.setPrecoTotal();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPrecoUnidade() {
        return precoUnidade;
    }

    public void setPrecoUnidade(double precoUnidade) {
        this.precoUnidade = precoUnidade;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal() {
        this.precoTotal = this.precoUnidade * this.quantidade;
    }

    @Override
    public String toString() {
        return  nome + "{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                ", quantidade=" + quantidade +
                ", precoUnidade=" + precoUnidade +
                ", precoTotal=" + precoTotal +
                '}';
    }
}
