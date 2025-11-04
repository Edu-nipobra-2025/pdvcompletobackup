package com.br.pdvpostocombustivel.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.Date;

@Entity
@Table(name = "custos")
public class Custo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @PositiveOrZero
    private double imposto;

    @NotNull
    @PositiveOrZero
    private double frete;

    @NotNull
    @PositiveOrZero
    @Column(name = "custo_variavel")
    private double custoVariavel;

    @NotNull
    @PositiveOrZero
    @Column(name = "custo_fixo")
    private double custoFixo;

    @NotNull
    @PositiveOrZero
    @Column(name = "margem_lucro")
    private double margemLucro;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_processamento")
    private Date dataProcessamento;

    // construtor com parâmetros
    public Custo(double imposto, double frete, double custoVariavel, double custoFixo, double margemLucro, Date dataProcessamento) {
        this.imposto = imposto;
        this.frete = frete;
        this.custoVariavel = custoVariavel;
        this.custoFixo = custoFixo;
        this.margemLucro = margemLucro;
        this.dataProcessamento = dataProcessamento;
    }
    public Custo() {

        super();
    }

    // getters
    public Long getId() {
        return id;
    }

    public double getImposto() {
        return imposto;
    }

    public double getFrete() {
        return frete;
    }

    public double getCustoVariavel() {
        return custoVariavel;
    }

    public double getCustoFixo() {
        return custoFixo;
    }

    public double getMargemLucro() {
        return margemLucro;
    }

    public Date getDataProcessamento() {
        return dataProcessamento;
    }

    // setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setImposto(double imposto) {
        this.imposto = imposto;
    }

    public void setFrete(double frete) {
        this.frete = frete;
    }

    public void setCustoVariavel(double custoVariavel) {
        this.custoVariavel = custoVariavel;
    }

    public void setCustoFixo(double custoFixo) {
        this.custoFixo = custoFixo;
    }

    public void setMargemLucro(double margemLucro) {
        this.margemLucro = margemLucro;
    }

    public void setDataProcessamento(Date dataProcessamento) {
        this.dataProcessamento = dataProcessamento;
    }
}
