package com.br.pdvpostocombustivel.api.acesso;

import com.br.pdvpostocombustivel.api.acesso.dto.AcessoRequest;
import com.br.pdvpostocombustivel.api.acesso.dto.AcessoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/acessos")
@Tag(name = "Acessos", description = "Operações relacionadas a acessos de usuários")
public class AcessoController {

    @Autowired
    private AcessoService acessoService;

    @PostMapping
    @Operation(summary = "Criar novo acesso", description = "Cria um novo acesso no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Acesso criado com sucesso",
            content = @Content(schema = @Schema(implementation = AcessoResponse.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<AcessoResponse> criar(@Valid @RequestBody AcessoRequest request) {
        AcessoResponse response = acessoService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar acesso por ID", description = "Retorna um acesso específico pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Acesso encontrado",
            content = @Content(schema = @Schema(implementation = AcessoResponse.class))),
        @ApiResponse(responseCode = "404", description = "Acesso não encontrado")
    })
    public ResponseEntity<AcessoResponse> buscarPorId(
            @Parameter(description = "ID do acesso") @PathVariable Long id) {
        return ResponseEntity.ok(acessoService.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar todos os acessos", description = "Retorna uma lista com todos os acessos")
    @ApiResponse(responseCode = "200", description = "Lista de acessos retornada com sucesso",
        content = @Content(schema = @Schema(implementation = AcessoResponse.class)))
    public ResponseEntity<List<AcessoResponse>> listarTodos() {
        return ResponseEntity.ok(acessoService.listarTodos());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar acesso", description = "Atualiza um acesso existente pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Acesso atualizado com sucesso",
            content = @Content(schema = @Schema(implementation = AcessoResponse.class))),
        @ApiResponse(responseCode = "404", description = "Acesso não encontrado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<AcessoResponse> atualizar(
            @Parameter(description = "ID do acesso") @PathVariable Long id,
            @Valid @RequestBody AcessoRequest request) {
        return ResponseEntity.ok(acessoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar acesso", description = "Remove um acesso do sistema pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Acesso deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Acesso não encontrado")
    })
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do acesso") @PathVariable Long id) {
        acessoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
