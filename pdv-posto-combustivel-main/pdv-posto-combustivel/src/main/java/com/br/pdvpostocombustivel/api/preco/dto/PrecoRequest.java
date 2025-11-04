package com.br.pdvpostocombustivel.api.preco.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;

@Schema(description = "DTO para requisição de Preço")
public class PrecoRequest {
    @NotNull
    @PositiveOrZero
    @Schema(description = "Valor do preço", example = "5.99")
    private BigDecimal valor;

    @Schema(description = "Data da alteração", example = "2025-10-15")
    private Date dataAlteracao;

    @Schema(description = "Hora da alteração", example = "10:30:00")
    private Date horaAlteracao;

    // Getters e Setters
    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public Date getHoraAlteracao() {
        return horaAlteracao;
    }

    public void setHoraAlteracao(Date horaAlteracao) {
        this.horaAlteracao = horaAlteracao;
    }
}
