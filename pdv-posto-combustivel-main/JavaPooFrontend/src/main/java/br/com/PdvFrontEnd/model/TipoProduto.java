package com.br.pdvpostocombustivel.enums;

public enum TipoProduto {
    COMBUSTIVEL("Produto de Combustivel"),
    LUBRIFICANTE("Produto Lubrificante");

    private final String descricao;

    private TipoProduto(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
