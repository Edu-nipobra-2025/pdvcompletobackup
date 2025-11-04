package com.br.pdvpostocombustivel.api.preco;

import com.br.pdvpostocombustivel.api.preco.dto.PrecoRequest;
import com.br.pdvpostocombustivel.api.preco.dto.PrecoResponse;
import com.br.pdvpostocombustivel.domain.entity.Preco;
import com.br.pdvpostocombustivel.domain.repository.PrecoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Date;

@Service
public class PrecoService {

    @Autowired
    private PrecoRepository precoRepository;

    @Transactional
    public PrecoResponse criar(PrecoRequest request) {
        Preco preco = new Preco();
        preco.setValor(request.getValor());
        preco.setDataAlteracao(request.getDataAlteracao() != null ? request.getDataAlteracao() : new Date());
        preco.setHoraAlteracao(request.getHoraAlteracao() != null ? request.getHoraAlteracao() : new Date());

        preco = precoRepository.save(preco);
        return converterParaResponse(preco);
    }

    public PrecoResponse buscarPorId(Long id) {
        Preco preco = precoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Preço não encontrado"));
        return converterParaResponse(preco);
    }

    public List<PrecoResponse> listarTodos() {
        return precoRepository.findAll().stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public PrecoResponse atualizar(Long id, PrecoRequest request) {
        Preco preco = precoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Preço não encontrado"));

        preco.setValor(request.getValor());
        if (request.getDataAlteracao() != null) {
            preco.setDataAlteracao(request.getDataAlteracao());
        }
        if (request.getHoraAlteracao() != null) {
            preco.setHoraAlteracao(request.getHoraAlteracao());
        }

        preco = precoRepository.save(preco);
        return converterParaResponse(preco);
    }

    @Transactional
    public void deletar(Long id) {
        if (!precoRepository.existsById(id)) {
            throw new EntityNotFoundException("Preço não encontrado");
        }
        precoRepository.deleteById(id);
    }

    private PrecoResponse converterParaResponse(Preco preco) {
        PrecoResponse response = new PrecoResponse();
        response.setId(preco.getId());
        response.setValor(preco.getValor());
        response.setDataAlteracao(preco.getDataAlteracao());
        response.setHoraAlteracao(preco.getHoraAlteracao());
        return response;
    }
}
