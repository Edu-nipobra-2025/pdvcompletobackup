package com.br.pdvpostocombustivel.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "contatos")
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String telefone;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String endereco;

    /*constructor*/
    public Contato(String telefone, String email, String endereco){
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    public Contato() {

        super();
    }
    /*getters*/
    public Long getId() {
        return id;
    }

    public String getTelefone() {
        return telefone;
    }
    public String getEmail() {
        return email;
    }
    public String getEndereco() {
        return endereco;
    }

    /*setters*/
    public void setId(Long id) {
        this.id = id;
    }

    public void setTelefone (String telefone){
        this.telefone = telefone;
    }
    public void setEmail (String email){
        this.email = email;
    }
    public void setEndereco (String endereco){
        this.endereco = endereco;
    }
}