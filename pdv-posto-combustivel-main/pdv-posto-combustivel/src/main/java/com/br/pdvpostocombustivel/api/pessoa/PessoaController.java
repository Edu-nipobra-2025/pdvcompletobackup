package  com.br.pdvpostocombustivel.api.pessoa;


import  com.br.pdvpostocombustivel.api.pessoa.dto.PessoaRequest;
import  com.br.pdvpostocombustivel.api.pessoa.dto.PessoaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pessoas")
public class PessoaController {

    private final PessoaService service;

    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PessoaResponse create(@RequestBody PessoaRequest req) {
        return service.create(req);
    }

    @GetMapping("/{id}")
    public PessoaResponse get(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping(params = "cpfCnpj")
    public PessoaResponse getByCpf(@RequestParam String cpfCnpj) {
        return service.getByCpfCnpj(cpfCnpj);
    }

    @GetMapping
    public Page<PessoaResponse> list(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "10") int size,
                                     @RequestParam(defaultValue = "id") String sortBy,
                                     @RequestParam(defaultValue = "ASC") Sort.Direction dir) {
        return service.list(page, size, sortBy, dir);
    }

    @PutMapping("/{id}")
    public PessoaResponse update(@PathVariable Long id, @RequestBody PessoaRequest req) {
        return service.update(id, req);
    }

    @PatchMapping("/{id}")
    public PessoaResponse patch(@PathVariable Long id, @RequestBody PessoaRequest req) {
        return service.patch(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

