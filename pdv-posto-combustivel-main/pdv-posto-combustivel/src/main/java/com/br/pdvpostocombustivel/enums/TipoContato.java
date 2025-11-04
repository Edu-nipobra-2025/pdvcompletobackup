package com.br.pdvpostocombustivel.enums;

public enum TipoContato {

    EMAIL("Contato E-mail"),
    TELEFONE("Contato Telefone"),
    CELULAR("Contato Celular");

    private final String descricao;

    private TipoContato(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
