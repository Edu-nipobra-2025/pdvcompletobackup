package  com.br.pdvpostocombustivel.api.contato;

import  com.br.pdvpostocombustivel.api.contato.dto.ContatoRequest;
import  com.br.pdvpostocombustivel.api.contato.dto.ContatoResponse;
import  com.br.pdvpostocombustivel.domain.entity.Contato;
import com.br.pdvpostocombustivel.domain.repository.ContatoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    @Transactional
    public ContatoResponse criar(ContatoRequest request) {
        Contato contato = new Contato();
        contato.setTelefone(request.getTelefone());
        contato.setEmail(request.getEmail());
        contato.setEndereco(request.getEndereco());

        contato = contatoRepository.save(contato);
        return converterParaResponse(contato);
    }

    public ContatoResponse buscarPorId(Long id) {
        Contato contato = contatoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contato não encontrado"));
        return converterParaResponse(contato);
    }

    public List<ContatoResponse> listarTodos() {
        return contatoRepository.findAll().stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public ContatoResponse atualizar(Long id, ContatoRequest request) {
        Contato contato = contatoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contato não encontrado"));

        contato.setTelefone(request.getTelefone());
        contato.setEmail(request.getEmail());
        contato.setEndereco(request.getEndereco());

        contato = contatoRepository.save(contato);
        return converterParaResponse(contato);
    }

    @Transactional
    public void deletar(Long id) {
        if (!contatoRepository.existsById(id)) {
            throw new EntityNotFoundException("Contato não encontrado");
        }
        contatoRepository.deleteById(id);
    }

    private ContatoResponse converterParaResponse(Contato contato) {
        ContatoResponse response = new ContatoResponse();
        response.setId(contato.getId());
        response.setTelefone(contato.getTelefone());
        response.setEmail(contato.getEmail());
        response.setEndereco(contato.getEndereco());
        return response;
    }
}
