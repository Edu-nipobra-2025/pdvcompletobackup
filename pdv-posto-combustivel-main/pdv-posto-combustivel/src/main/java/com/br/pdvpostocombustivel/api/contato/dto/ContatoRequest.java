package  com.br.pdvpostocombustivel.api.contato.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO para requisição de Contato")
public class ContatoRequest {
    @NotBlank
    @Schema(description = "Número de telefone", example = "(11) 99999-8888")
    private String telefone;

    @NotBlank
    @Email
    @Schema(description = "Endereço de email", example = "usuario@email.com")
    private String email;

    @NotBlank
    @Schema(description = "Endereço físico", example = "Rua Example, 123")
    private String endereco;

    // Getters e Setters
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
