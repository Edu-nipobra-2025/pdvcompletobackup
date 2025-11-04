package  com.br.pdvpostocombustivel.api.contato.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para resposta de Contato")
public class ContatoResponse {
    @Schema(description = "ID do contato")
    private Long id;

    @Schema(description = "Número de telefone", example = "(11) 99999-8888")
    private String telefone;

    @Schema(description = "Endereço de email", example = "usuario@email.com")
    private String email;

    @Schema(description = "Endereço físico", example = "Rua Example, 123")
    private String endereco;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
