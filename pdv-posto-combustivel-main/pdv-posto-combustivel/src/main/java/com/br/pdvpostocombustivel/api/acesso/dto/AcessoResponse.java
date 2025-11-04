package com.br.pdvpostocombustivel.api.acesso.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para resposta de Acesso")
public class AcessoResponse {
    @Schema(description = "ID do acesso")
    private Long id;

    @Schema(description = "Nome de usuário", example = "joao.silva")
    private String usuario;

    // Não retornamos a senha na resposta por segurança

    // Getters e Setters
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
}
