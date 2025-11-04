package com.br.pdvpostocombustivel.domain.entity;
import java.util.Date;
import java.math.BigDecimal;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "estoques")
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @PositiveOrZero
    private BigDecimal quantidade;

    @NotBlank
    @Column(name = "local_tanque")
    private String localTanque;

    @NotBlank
    @Column(name = "local_endereco")
    private String localEndereco;

    @NotBlank
    @Column(name = "lote_fabricacao")
    private String loteFabricacao;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "data_validade")
    private Date dataValidade;

    // construtor
    public Estoque(BigDecimal quantidade, String localTanque, String localEndereco, String loteFabricacao, Date dataValidade) {
        this.quantidade = quantidade;
        this.localTanque = localTanque;
        this.localEndereco = localEndereco;
        this.loteFabricacao = loteFabricacao;
        this.dataValidade = dataValidade;
    }
    public Estoque() {

        super();
    }

    // getters
    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public String getLocalTanque() {
        return localTanque;
    }

    public String getLocalEndereco() {
        return localEndereco;
    }

    public String getLoteFabricacao() {
        return loteFabricacao;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public Long getId() {
        return id;
    }

    // setters
    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public void setLocalTanque(String localTanque) {
        this.localTanque = localTanque;
    }

    public void setLocalEndereco(String localEndereco) {
        this.localEndereco = localEndereco;
    }

    public void setLoteFabricacao(String loteFabricacao) {
        this.loteFabricacao = loteFabricacao;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
