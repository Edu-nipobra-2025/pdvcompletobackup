package  com.br.pdvpostocombustivel.api.custo;

import  com.br.pdvpostocombustivel.api.custo.dto.CustoRequest;
import  com.br.pdvpostocombustivel.api.custo.dto.CustoResponse;
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
@RequestMapping("/api/custos")
@Tag(name = "Custos", description = "Operações relacionadas a custos")
public class CustoController {

    @Autowired
    private CustoService custoService;

    @PostMapping
    @Operation(summary = "Criar novo custo", description = "Cria um novo registro de custo no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Custo criado com sucesso",
            content = @Content(schema = @Schema(implementation = CustoResponse.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<CustoResponse> criar(@Valid @RequestBody CustoRequest request) {
        CustoResponse response = custoService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar custo por ID", description = "Retorna um custo específico pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Custo encontrado",
            content = @Content(schema = @Schema(implementation = CustoResponse.class))),
        @ApiResponse(responseCode = "404", description = "Custo não encontrado")
    })
    public ResponseEntity<CustoResponse> buscarPorId(
            @Parameter(description = "ID do custo") @PathVariable Long id) {
        return ResponseEntity.ok(custoService.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar todos os custos", description = "Retorna uma lista com todos os custos")
    @ApiResponse(responseCode = "200", description = "Lista de custos retornada com sucesso",
        content = @Content(schema = @Schema(implementation = CustoResponse.class)))
    public ResponseEntity<List<CustoResponse>> listarTodos() {
        return ResponseEntity.ok(custoService.listarTodos());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar custo", description = "Atualiza um custo existente pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Custo atualizado com sucesso",
            content = @Content(schema = @Schema(implementation = CustoResponse.class))),
        @ApiResponse(responseCode = "404", description = "Custo não encontrado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<CustoResponse> atualizar(
            @Parameter(description = "ID do custo") @PathVariable Long id,
            @Valid @RequestBody CustoRequest request) {
        return ResponseEntity.ok(custoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar custo", description = "Remove um custo do sistema pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Custo deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Custo não encontrado")
    })
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do custo") @PathVariable Long id) {
        custoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
