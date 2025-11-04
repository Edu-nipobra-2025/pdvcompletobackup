package com.br.pdvpostocombustivel.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "acessos")
public class Acesso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String usuario;

    @NotBlank
    private String senha;

    /*constructor*/
    public Acesso(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }
    public Acesso() {

        super();
    }

    /*getters*/
    public Long getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    /*setters*/
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}