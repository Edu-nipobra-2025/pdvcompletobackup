package com.br.pdvpostocombustivel.api.estoque.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.util.Date;

@Schema(description = "DTO para resposta de Estoque")
public class EstoqueResponse {
    @Schema(description = "ID do estoque")
    private Long id;

    @Schema(description = "Quantidade em estoque", example = "1000.50")
    private BigDecimal quantidade;

    @Schema(description = "Localização do tanque", example = "TANQUE A")
    private String localTanque;

    @Schema(description = "Endereço do local", example = "ESTOQUE PRINCIPAL")
    private String localEndereco;

    @Schema(description = "Lote de fabricação", example = "LOTE-001")
    private String loteFabricacao;

    @Schema(description = "Data de validade", example = "2025-12-31")
    private Date dataValidade;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
