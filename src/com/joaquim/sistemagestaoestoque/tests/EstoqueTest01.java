package com.joaquim.sistemagestaoestoque.tests;

import com.joaquim.sistemagestaoestoque.model.*;
import com.joaquim.sistemagestaoestoque.service.Estoque;

public class EstoqueTest01 {
    public static void main(String[] args) {
        Estoque estoque = new Estoque();

        Fornecedor f1 = new Fornecedor("Empresa LTDA", "129391210192", "empresa1@dominio.com");

        estoque.adicionarProduto("Arroz Branco", "Arroz Branco", Categoria.ALIMENTO, 12.90);
        estoque.adicionarProduto("Mouse Logitech G903", "Mouse Logitech G903 Preto Sem Fio", Categoria.ELETRONICO, 550);

        estoque.fornecerProduto(f1, 1, 300);
        estoque.fornecerProduto(f1, 2, 20);


        System.out.println();
        System.out.println(estoque);
        System.out.println();

        estoque.saidaProduto(f1, 2, 5);

        System.out.println();
        System.out.println(estoque);
        System.out.println();

        estoque.imprimirMovimentacoes();
    }
}
