package com.joaquim.sistemagestaoestoque.model;

import com.joaquim.sistemagestaoestoque.service.Estoque;

public class Movimentacao {
    private String nome;
    private int produto_id;
    private int quantidade;
    private Tipo tipo;
    private double valorTotal;
    private String data_hora;

    public Movimentacao(String nome, int produto_id, int quantidade, double valorTotal, Tipo tipo, String data_hora) {
        this.produto_id = produto_id;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.valorTotal = valorTotal;
        this.data_hora = data_hora;
        this.nome = nome;
    }

    public int getProduto_id() {
        return produto_id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public String getData_hora() {
        return data_hora;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "------------" +
                "\nProduto: " + this.nome +
                "\nID do produto: " + this.produto_id +
                "\nQuantidade: " + this.quantidade +
                "\nValor Total: " + this.valorTotal +
                "\nTipo: " + this.tipo.name() +
                "\nData/Horário: " + this.data_hora;
    }
}
