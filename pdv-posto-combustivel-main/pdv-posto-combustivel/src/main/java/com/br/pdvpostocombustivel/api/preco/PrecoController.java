package com.br.pdvpostocombustivel.api.preco;

import com.br.pdvpostocombustivel.api.preco.dto.PrecoRequest;
import com.br.pdvpostocombustivel.api.preco.dto.PrecoResponse;
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
@RequestMapping("/api/precos")
@Tag(name = "Preços", description = "Operações relacionadas a preços")
public class PrecoController {

    @Autowired
    private PrecoService precoService;

    @PostMapping
    @Operation(summary = "Criar novo preço", description = "Cria um novo registro de preço no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Preço criado com sucesso",
            content = @Content(schema = @Schema(implementation = PrecoResponse.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<PrecoResponse> criar(@Valid @RequestBody PrecoRequest request) {
        PrecoResponse response = precoService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar preço por ID", description = "Retorna um preço específico pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Preço encontrado",
            content = @Content(schema = @Schema(implementation = PrecoResponse.class))),
        @ApiResponse(responseCode = "404", description = "Preço não encontrado")
    })
    public ResponseEntity<PrecoResponse> buscarPorId(
            @Parameter(description = "ID do preço") @PathVariable Long id) {
        return ResponseEntity.ok(precoService.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar todos os preços", description = "Retorna uma lista com todos os preços")
    @ApiResponse(responseCode = "200", description = "Lista de preços retornada com sucesso",
        content = @Content(schema = @Schema(implementation = PrecoResponse.class)))
    public ResponseEntity<List<PrecoResponse>> listarTodos() {
        return ResponseEntity.ok(precoService.listarTodos());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar preço", description = "Atualiza um preço existente pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Preço atualizado com sucesso",
            content = @Content(schema = @Schema(implementation = PrecoResponse.class))),
        @ApiResponse(responseCode = "404", description = "Preço não encontrado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<PrecoResponse> atualizar(
            @Parameter(description = "ID do preço") @PathVariable Long id,
            @Valid @RequestBody PrecoRequest request) {
        return ResponseEntity.ok(precoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar preço", description = "Remove um preço do sistema pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Preço deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Preço não encontrado")
    })
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do preço") @PathVariable Long id) {
        precoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
