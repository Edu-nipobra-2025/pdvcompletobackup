package com.br.pdvpostocombustivel.api.acesso;

import  com.br.pdvpostocombustivel.api.acesso.dto.AcessoRequest;
import  com.br.pdvpostocombustivel.api.acesso.dto.AcessoResponse;
import  com.br.pdvpostocombustivel.domain.entity.Acesso;
import com.br.pdvpostocombustivel.domain.repository.AcessoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AcessoService {

    @Autowired
    private AcessoRepository acessoRepository;

    @Transactional
    public AcessoResponse criar(AcessoRequest request) {
        Acesso acesso = new Acesso();
        acesso.setUsuario(request.getUsuario());
        acesso.setSenha(request.getSenha());

        acesso = acessoRepository.save(acesso);
        return converterParaResponse(acesso);
    }

    public AcessoResponse buscarPorId(Long id) {
        Acesso acesso = acessoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Acesso não encontrado"));
        return converterParaResponse(acesso);
    }

    public List<AcessoResponse> listarTodos() {
        return acessoRepository.findAll().stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public AcessoResponse atualizar(Long id, AcessoRequest request) {
        Acesso acesso = acessoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Acesso não encontrado"));

        acesso.setUsuario(request.getUsuario());
        if (request.getSenha() != null && !request.getSenha().isEmpty()) {
            acesso.setSenha(request.getSenha());
        }

        acesso = acessoRepository.save(acesso);
        return converterParaResponse(acesso);
    }

    @Transactional
    public void deletar(Long id) {
        if (!acessoRepository.existsById(id)) {
            throw new EntityNotFoundException("Acesso não encontrado");
        }
        acessoRepository.deleteById(id);
    }

    private AcessoResponse converterParaResponse(Acesso acesso) {
        AcessoResponse response = new AcessoResponse();
        response.setId(acesso.getId());
        response.setUsuario(acesso.getUsuario());
        return response;
    }
}
