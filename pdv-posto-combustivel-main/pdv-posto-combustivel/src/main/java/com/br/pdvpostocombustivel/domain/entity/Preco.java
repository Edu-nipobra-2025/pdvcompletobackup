package com.br.pdvpostocombustivel.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "precos")
public class Preco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @PositiveOrZero
    private BigDecimal valor;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "data_alteracao")
    private Date dataAlteracao;

    @NotNull
    @Temporal(TemporalType.TIME)
    @Column(name = "hora_alteracao")
    private Date horaAlteracao;

    // construtor
    public Preco(BigDecimal valor, Date dataAlteracao, Date horaAlteracao) {
        this.valor = valor;
        this.dataAlteracao = dataAlteracao;
        this.horaAlteracao = horaAlteracao;
    }

    public Preco() {

        super();
    }

    // getters
    public BigDecimal getValor() {
        return valor;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public Date getHoraAlteracao() {
        return horaAlteracao;
    }

    public Long getId() {
        return id;
    }

    // setters
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public void setHoraAlteracao(Date horaAlteracao) {
        this.horaAlteracao = horaAlteracao;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
