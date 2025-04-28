package com.joaquim.sistemagestaoestoque.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movimentacao {
    private LocalDateTime dataHora;
    private Tipo tipo;
    private int quantidade;
    private Produto produto;

    public Movimentacao(Produto produto, Tipo tipo, int quantidade) {
        this.dataHora = LocalDateTime.now();
        this.produto = produto;
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    @Override
    public String toString() {
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "Movimentacao{" +
                "dataHora=" + dataHora.format(dataFormatada) +
                ", tipo=" + tipo +
                ", quantidade=" + quantidade +
                ", produto=" + produto +
                '}';
    }
}
