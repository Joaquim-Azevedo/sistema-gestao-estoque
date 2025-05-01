package com.joaquim.sistemagestaoestoque.tests;

import com.joaquim.sistemagestaoestoque.model.*;
import com.joaquim.sistemagestaoestoque.service.Estoque;

public class EstoqueTest01 {
    public static void main(String[] args) {
        Estoque estoque = new Estoque();

        System.out.println(estoque.listarProdutos());

    }
}
