package com.br.pdvpostocombustivel.api.acesso.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO para requisição de Acesso")
public class AcessoRequest {
    @NotBlank
    @Schema(description = "Nome de usuário", example = "joao.silva")
    private String usuario;

    @NotBlank
    @Schema(description = "Senha do usuário", example = "senha123")
    private String senha;

    // Getters e Setters
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
