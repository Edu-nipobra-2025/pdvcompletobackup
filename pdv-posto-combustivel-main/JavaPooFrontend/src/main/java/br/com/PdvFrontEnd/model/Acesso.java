package br.com.PdvFrontEnd.model;
public class Acesso {
    /*atributos*/
    private Long id;
    private String usuario;
    private String senha;

    /*constructor*/
    public Acesso(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
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