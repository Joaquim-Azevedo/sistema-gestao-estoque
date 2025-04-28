package com.joaquim.sistemagestaoestoque.model;

import com.joaquim.sistemagestaoestoque.service.Estoque;

public final class Fornecedor {
    private String nome;
    private String cnpj;
    private String contato;

    public Fornecedor(String nome, String cnpj, String contato) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.contato = contato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}
