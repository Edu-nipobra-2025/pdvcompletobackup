package  com.br.pdvpostocombustivel.api.estoque;

import  com.br.pdvpostocombustivel.api.estoque.dto.EstoqueRequest;
import  com.br.pdvpostocombustivel.api.estoque.dto.EstoqueResponse;
import  com.br.pdvpostocombustivel.domain.entity.Estoque;
import com.br.pdvpostocombustivel.domain.repository.EstoqueRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Transactional
    public EstoqueResponse criar(EstoqueRequest request) {
        Estoque estoque = new Estoque();
        estoque.setQuantidade(request.getQuantidade());
        estoque.setLocalTanque(request.getLocalTanque());
        estoque.setLocalEndereco(request.getLocalEndereco());
        estoque.setLoteFabricacao(request.getLoteFabricacao());
        estoque.setDataValidade(request.getDataValidade());

        estoque = estoqueRepository.save(estoque);
        return converterParaResponse(estoque);
    }

    public EstoqueResponse buscarPorId(Long id) {
        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estoque não encontrado"));
        return converterParaResponse(estoque);
    }

    public List<EstoqueResponse> listarTodos() {
        return estoqueRepository.findAll().stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public EstoqueResponse atualizar(Long id, EstoqueRequest request) {
        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estoque não encontrado"));

        estoque.setQuantidade(request.getQuantidade());
        estoque.setLocalTanque(request.getLocalTanque());
        estoque.setLocalEndereco(request.getLocalEndereco());
        estoque.setLoteFabricacao(request.getLoteFabricacao());
        estoque.setDataValidade(request.getDataValidade());

        estoque = estoqueRepository.save(estoque);
        return converterParaResponse(estoque);
    }

    @Transactional
    public void deletar(Long id) {
        if (!estoqueRepository.existsById(id)) {
            throw new EntityNotFoundException("Estoque não encontrado");
        }
        estoqueRepository.deleteById(id);
    }

    private EstoqueResponse converterParaResponse(Estoque estoque) {
        EstoqueResponse response = new EstoqueResponse();
        response.setId(estoque.getId());
        response.setQuantidade(estoque.getQuantidade());
        response.setLocalTanque(estoque.getLocalTanque());
        response.setLocalEndereco(estoque.getLocalEndereco());
        response.setLoteFabricacao(estoque.getLoteFabricacao());
        response.setDataValidade(estoque.getDataValidade());
        return response;
    }
}
