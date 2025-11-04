package br.com.PdvFrontEnd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AcessoResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("usuario")
    private String usuario;

    @JsonProperty("senha")
    private String senha;

    public AcessoResponse() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

