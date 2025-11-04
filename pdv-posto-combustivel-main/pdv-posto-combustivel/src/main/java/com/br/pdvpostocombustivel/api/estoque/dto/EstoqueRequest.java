package com.br.pdvpostocombustivel.api.estoque.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;

@Schema(description = "DTO para requisição de Estoque")
public class EstoqueRequest {
    @NotNull
    @PositiveOrZero
    @Schema(description = "Quantidade em estoque", example = "1000.50")
    private BigDecimal quantidade;

    @NotBlank
    @Schema(description = "Localização do tanque", example = "TANQUE A")
    private String localTanque;

    @NotBlank
    @Schema(description = "Endereço do local", example = "ESTOQUE PRINCIPAL")
    private String localEndereco;

    @NotBlank
    @Schema(description = "Lote de fabricação", example = "LOTE-001")
    private String loteFabricacao;

    @NotNull
    @Schema(description = "Data de validade", example = "2025-12-31")
    private Date dataValidade;

    // Getters e Setters
    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public String getLocalTanque() {
        return localTanque;
    }

    public void setLocalTanque(String localTanque) {
        this.localTanque = localTanque;
    }

    public String getLocalEndereco() {
        return localEndereco;
    }

    public void setLocalEndereco(String localEndereco) {
        this.localEndereco = localEndereco;
    }

    public String getLoteFabricacao() {
        return loteFabricacao;
    }

    public void setLoteFabricacao(String loteFabricacao) {
        this.loteFabricacao = loteFabricacao;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }
}
