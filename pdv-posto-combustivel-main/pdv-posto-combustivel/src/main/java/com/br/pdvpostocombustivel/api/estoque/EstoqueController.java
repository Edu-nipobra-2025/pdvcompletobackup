package  com.br.pdvpostocombustivel.api.estoque;

import com.br.pdvpostocombustivel.api.estoque.dto.EstoqueRequest;
import com.br.pdvpostocombustivel.api.estoque.dto.EstoqueResponse;
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
@RequestMapping("/api/estoques")
@Tag(name = "Estoques", description = "Operações relacionadas ao controle de estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @PostMapping
    @Operation(summary = "Criar novo registro de estoque", description = "Cria um novo registro de estoque no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Estoque criado com sucesso",
            content = @Content(schema = @Schema(implementation = EstoqueResponse.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<EstoqueResponse> criar(@Valid @RequestBody EstoqueRequest request) {
        EstoqueResponse response = estoqueService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar estoque por ID", description = "Retorna um registro de estoque específico pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estoque encontrado",
            content = @Content(schema = @Schema(implementation = EstoqueResponse.class))),
        @ApiResponse(responseCode = "404", description = "Estoque não encontrado")
    })
    public ResponseEntity<EstoqueResponse> buscarPorId(
            @Parameter(description = "ID do estoque") @PathVariable Long id) {
        return ResponseEntity.ok(estoqueService.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar todos os registros de estoque", description = "Retorna uma lista com todos os registros de estoque")
    @ApiResponse(responseCode = "200", description = "Lista de estoques retornada com sucesso",
        content = @Content(schema = @Schema(implementation = EstoqueResponse.class)))
    public ResponseEntity<List<EstoqueResponse>> listarTodos() {
        return ResponseEntity.ok(estoqueService.listarTodos());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar estoque", description = "Atualiza um registro de estoque existente pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estoque atualizado com sucesso",
            content = @Content(schema = @Schema(implementation = EstoqueResponse.class))),
        @ApiResponse(responseCode = "404", description = "Estoque não encontrado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<EstoqueResponse> atualizar(
            @Parameter(description = "ID do estoque") @PathVariable Long id,
            @Valid @RequestBody EstoqueRequest request) {
        return ResponseEntity.ok(estoqueService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar estoque", description = "Remove um registro de estoque do sistema pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Estoque deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Estoque não encontrado")
    })
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do estoque") @PathVariable Long id) {
        estoqueService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
