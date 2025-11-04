package br.com.PdvFrontEnd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AcessoRequest {
    @JsonProperty("usuario")
    private String usuario;

    @JsonProperty("senha")
    private String senha;

    public AcessoRequest() {}

    public AcessoRequest(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

