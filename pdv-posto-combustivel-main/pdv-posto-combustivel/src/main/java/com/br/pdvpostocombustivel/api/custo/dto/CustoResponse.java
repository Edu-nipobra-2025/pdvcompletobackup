package  com.br.pdvpostocombustivel.api.custo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;

@Schema(description = "DTO para resposta de Custo")
public class CustoResponse {
    @Schema(description = "ID do custo")
    private Long id;

    @Schema(description = "Valor do imposto", example = "10.5")
    private Double imposto;

    @Schema(description = "Valor do frete", example = "25.0")
    private Double frete;

    @Schema(description = "Valor do custo variável", example = "80.0")
    private Double custoVariavel;

    @Schema(description = "Valor do custo fixo", example = "150.0")
    private Double custoFixo;

    @Schema(description = "Valor da margem de lucro", example = "20.0")
    private Double margemLucro;

    @Schema(description = "Data de processamento", example = "2025-10-15T10:30:00")
    private Date dataProcessamento;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getImposto() {
        return imposto;
    }

    public void setImposto(Double imposto) {
        this.imposto = imposto;
    }

    public Double getFrete() {
        return frete;
    }

    public void setFrete(Double frete) {
        this.frete = frete;
    }

    public Double getCustoVariavel() {
        return custoVariavel;
    }

    public void setCustoVariavel(Double custoVariavel) {
        this.custoVariavel = custoVariavel;
    }

    public Double getCustoFixo() {
        return custoFixo;
    }

    public void setCustoFixo(Double custoFixo) {
        this.custoFixo = custoFixo;
    }

    public Double getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(Double margemLucro) {
        this.margemLucro = margemLucro;
    }

    public Date getDataProcessamento() {
        return dataProcessamento;
    }

    public void setDataProcessamento(Date dataProcessamento) {
        this.dataProcessamento = dataProcessamento;
    }
}
