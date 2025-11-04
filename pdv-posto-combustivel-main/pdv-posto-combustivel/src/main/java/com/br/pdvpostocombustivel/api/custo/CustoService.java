package  com.br.pdvpostocombustivel.api.custo;

import  com.br.pdvpostocombustivel.api.custo.dto.CustoRequest;
import  com.br.pdvpostocombustivel.api.custo.dto.CustoResponse;
import  com.br.pdvpostocombustivel.domain.entity.Custo;
import com.br.pdvpostocombustivel.domain.repository.CustoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

@Service
public class CustoService {

    @Autowired
    private CustoRepository custoRepository;

    @Transactional
    public CustoResponse criar(CustoRequest request) {
        Custo custo = new Custo();
        custo.setImposto(request.getImposto());
        custo.setFrete(request.getFrete());
        custo.setCustoVariavel(request.getCustoVariavel());
        custo.setCustoFixo(request.getCustoFixo());
        custo.setMargemLucro(request.getMargemLucro());
        custo.setDataProcessamento(request.getDataProcessamento() != null ? request.getDataProcessamento() : new Date());

        custo = custoRepository.save(custo);
        return converterParaResponse(custo);
    }

    public CustoResponse buscarPorId(Long id) {
        Custo custo = custoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Custo não encontrado"));
        return converterParaResponse(custo);
    }

    public List<CustoResponse> listarTodos() {
        return custoRepository.findAll().stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public CustoResponse atualizar(Long id, CustoRequest request) {
        Custo custo = custoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Custo não encontrado"));

        custo.setImposto(request.getImposto());
        custo.setFrete(request.getFrete());
        custo.setCustoVariavel(request.getCustoVariavel());
        custo.setCustoFixo(request.getCustoFixo());
        custo.setMargemLucro(request.getMargemLucro());
        if (request.getDataProcessamento() != null) {
            custo.setDataProcessamento(request.getDataProcessamento());
        }

        custo = custoRepository.save(custo);
        return converterParaResponse(custo);
    }

    @Transactional
    public void deletar(Long id) {
        if (!custoRepository.existsById(id)) {
            throw new EntityNotFoundException("Custo não encontrado");
        }
        custoRepository.deleteById(id);
    }

    private CustoResponse converterParaResponse(Custo custo) {
        CustoResponse response = new CustoResponse();
        response.setId(custo.getId());
        response.setImposto(custo.getImposto());
        response.setFrete(custo.getFrete());
        response.setCustoVariavel(custo.getCustoVariavel());
        response.setCustoFixo(custo.getCustoFixo());
        response.setMargemLucro(custo.getMargemLucro());
        response.setDataProcessamento(custo.getDataProcessamento());
        return response;
    }
}
