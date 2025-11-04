package  com.br.pdvpostocombustivel.api.contato;

import  com.br.pdvpostocombustivel.api.contato.dto.ContatoRequest;
import  com.br.pdvpostocombustivel.api.contato.dto.ContatoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contatos")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @PostMapping
    public ResponseEntity<ContatoResponse> criar(@RequestBody ContatoRequest request) {
        ContatoResponse response = contatoService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ContatoResponse>> listarTodos() {
        List<ContatoResponse> contatos = contatoService.listarTodos();
        return ResponseEntity.ok(contatos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContatoResponse> buscarPorId(@PathVariable Long id) {
        ContatoResponse response = contatoService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContatoResponse> atualizar(@PathVariable Long id, @RequestBody ContatoRequest request) {
        ContatoResponse response = contatoService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        contatoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

