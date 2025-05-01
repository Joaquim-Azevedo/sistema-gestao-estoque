package com.joaquim.sistemagestaoestoque.model;

import java.util.Objects;

public final class Produto {
    private String nome;
    private String descricao;
    private int produto_id;
    private Categoria categoria;
    private int quantidade;
    private double precoUnidade;
    private double precoTotal;

    public Produto(String nome, String descricao, Categoria categoria, double precoUnidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.quantidade = 0;
        this.precoUnidade = precoUnidade;
        setPrecoTotal();
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

    public int getProduto_id() {
        return produto_id;
    }

    // ** USO RESTRITO AO DAO para setar o ID do produto resgatado do banco de dados.
    public void setProduto_id(int produto_id){
        this.produto_id = produto_id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
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
        return  "------------\n" +
                nome + ":" +
                "\nID: " + produto_id +
                "\nDescrição: " + descricao +
                "\nCategoria: " + categoria +
                "\nQuantidade: " + quantidade +
                "\nPreço Unitário: " + precoUnidade +
                "\nPreco Total: " + precoTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return nome.equalsIgnoreCase(produto.nome) && categoria == produto.categoria;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome.toLowerCase(), categoria);
    }
}
