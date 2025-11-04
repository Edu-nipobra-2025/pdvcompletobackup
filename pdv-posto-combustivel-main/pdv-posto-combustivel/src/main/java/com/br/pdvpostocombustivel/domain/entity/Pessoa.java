package com.br.pdvpostocombustivel.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(
        name = "pessoas",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_pessoas_cpf_cnpj", columnNames = "cpf_cnpj")
        }
)
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 200)
    @Column(name = "nome_completo", nullable = false, length = 200)
    private String nomeCompleto;

    @NotBlank
    @Size(max = 20)
    @Column(name = "cpf_cnpj", nullable = false, length = 20, unique = true)
    private String cpfCnpj;

    @NotNull
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @NotNull
    @Column(name = "tipo_pessoa", nullable = false, length = 15)
    private String tipoPessoa;

    @Column(name = "role", length = 50)
    private String role;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    public Pessoa() {}

    public Pessoa(String nomeCompleto, String cpfCnpj, LocalDate dataNascimento, String tipoPessoa) {
        this.nomeCompleto = nomeCompleto;
        this.cpfCnpj = cpfCnpj;
        this.dataNascimento = dataNascimento;
        this.tipoPessoa = tipoPessoa;
        this.ativo = true;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }

    public String getCpfCnpj() { return cpfCnpj; }
    public void setCpfCnpj(String cpfCnpj) { this.cpfCnpj = cpfCnpj; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getTipoPessoa() { return tipoPessoa; }
    public void setTipoPessoa(String tipoPessoa) { this.tipoPessoa = tipoPessoa; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }
}
