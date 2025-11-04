package com.br.pdvpostocombustivel.api.produto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO para requisição de Produto")
public class ProdutoRequest {
    @NotBlank
    @Schema(description = "Nome do produto", example = "Gasolina Comum")
    private String nome;

    @NotBlank
    @Schema(description = "Referência do produto", example = "GCOM-001")
    private String referencia;

    @NotBlank
    @Schema(description = "Fornecedor do produto", example = "Petrobras")
    private String fornecedor;

    @NotBlank
    @Schema(description = "Categoria do produto", example = "Combustível")
    private String categoria;

    @NotBlank
    @Schema(description = "Marca do produto", example = "Petrobras")
    private String marca;

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
