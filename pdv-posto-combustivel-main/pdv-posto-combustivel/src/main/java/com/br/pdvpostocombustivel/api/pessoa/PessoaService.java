package  com.br.pdvpostocombustivel.api.pessoa;


import com.br.pdvpostocombustivel.api.pessoa.dto.PessoaRequest;
import com.br.pdvpostocombustivel.api.pessoa.dto.PessoaResponse;
import com.br.pdvpostocombustivel.domain.entity.Pessoa;
import com.br.pdvpostocombustivel.domain.repository.PessoaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PessoaService {

    private final PessoaRepository repository;

    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public PessoaResponse create(PessoaRequest req) {
        Pessoa novaPessoa = toEntity(req);
        return toResponse(repository.save(novaPessoa));
    }

    // READ by ID
    @Transactional(readOnly = true)
    public PessoaResponse getById(Long id) {
        Pessoa p = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada. id=" + id));
        return toResponse(p);
    }

    // READ by CPF/CNPJ
    @Transactional(readOnly = true)
    public PessoaResponse getByCpfCnpj(String cpfCnpj) {
        Pessoa p = repository.findByCpfCnpj(cpfCnpj)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada. cpfCnpj=" + cpfCnpj));
        return toResponse(p);
    }

    // LIST paginado
    @Transactional(readOnly = true)
    public Page<PessoaResponse> list(int page, int size, String sortBy, Sort.Direction direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return repository.findAll(pageable).map(this::toResponse);
    }

    // UPDATE - substitui todos os campos
    public PessoaResponse update(Long id, PessoaRequest req) {
        Pessoa p = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada. id=" + id));

        if (req.getCpfCnpj() != null && !req.getCpfCnpj().equals(p.getCpfCnpj())) {
            validarUnicidadeCpfCnpj(req.getCpfCnpj(), id);
        }

        p.setNomeCompleto(req.getNomeCompleto());
        p.setCpfCnpj(req.getCpfCnpj());
        p.setDataNascimento(req.getDataNascimento());
        p.setTipoPessoa(req.getTipoPessoa());
        p.setRole(req.getRole());

        return toResponse(repository.save(p));
    }

    // PATCH - atualiza apenas campos não nulos
    public PessoaResponse patch(Long id, PessoaRequest req) {
        Pessoa p = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada. id=" + id));

        if (req.getNomeCompleto() != null) p.setNomeCompleto(req.getNomeCompleto());
        if (req.getCpfCnpj() != null) {
            if (!req.getCpfCnpj().equals(p.getCpfCnpj())) {
                validarUnicidadeCpfCnpj(req.getCpfCnpj(), id);
            }
            p.setCpfCnpj(req.getCpfCnpj());
        }
        if (req.getDataNascimento() != null) p.setDataNascimento(req.getDataNascimento());
        if (req.getTipoPessoa() != null) p.setTipoPessoa(req.getTipoPessoa());
        if (req.getRole() != null) p.setRole(req.getRole());

        return toResponse(repository.save(p));
    }

    // DELETE
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Pessoa não encontrada. id=" + id);
        }
        repository.deleteById(id);
    }

    // ---------- Helpers ----------
    private void validarUnicidadeCpfCnpj(String cpfCnpj, Long idAtual) {
        repository.findByCpfCnpj(cpfCnpj).ifPresent(existente -> {
            if (!existente.getId().equals(idAtual)) {
                throw new DataIntegrityViolationException("CPF/CNPJ já cadastrado: " + cpfCnpj);
            }
        });
    }

    private Pessoa toEntity(PessoaRequest req) {
        Pessoa pessoa = new Pessoa(
                req.getNomeCompleto(),
                req.getCpfCnpj(),
                req.getDataNascimento(),
                req.getTipoPessoa()
        );
        pessoa.setRole(req.getRole());
        return pessoa;
    }

    private PessoaResponse toResponse(Pessoa p) {
        PessoaResponse response = new PessoaResponse();
        response.setId(p.getId());
        response.setNomeCompleto(p.getNomeCompleto());
        response.setCpfCnpj(p.getCpfCnpj());
        response.setDataNascimento(p.getDataNascimento());
        response.setTipoPessoa(p.getTipoPessoa());
        response.setRole(p.getRole());
        return response;
    }
}
