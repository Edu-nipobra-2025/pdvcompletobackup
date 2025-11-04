package com.br.pdvpostocombustivel.api.produto;

import com.br.pdvpostocombustivel.api.produto.dto.ProdutoRequest;
import com.br.pdvpostocombustivel.api.produto.dto.ProdutoResponse;
import com.br.pdvpostocombustivel.domain.entity.Produto;
import com.br.pdvpostocombustivel.domain.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public ProdutoResponse criar(ProdutoRequest request) {
        Produto produto = new Produto();
        produto.setNome(request.getNome());
        produto.setReferencia(request.getReferencia());
        produto.setFornecedor(request.getFornecedor());
        produto.setCategoria(request.getCategoria());
        produto.setMarca(request.getMarca());

        produto = produtoRepository.save(produto);
        return converterParaResponse(produto);
    }

    public ProdutoResponse buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        return converterParaResponse(produto);
    }

    public List<ProdutoResponse> listarTodos() {
        return produtoRepository.findAll().stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProdutoResponse atualizar(Long id, ProdutoRequest request) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));

        produto.setNome(request.getNome());
        produto.setReferencia(request.getReferencia());
        produto.setFornecedor(request.getFornecedor());
        produto.setCategoria(request.getCategoria());
        produto.setMarca(request.getMarca());

        produto = produtoRepository.save(produto);
        return converterParaResponse(produto);
    }

    @Transactional
    public void deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new EntityNotFoundException("Produto não encontrado");
        }
        produtoRepository.deleteById(id);
    }

    private ProdutoResponse converterParaResponse(Produto produto) {
        ProdutoResponse response = new ProdutoResponse();
        response.setId(produto.getId());
        response.setNome(produto.getNome());
        response.setReferencia(produto.getReferencia());
        response.setFornecedor(produto.getFornecedor());
        response.setCategoria(produto.getCategoria());
        response.setMarca(produto.getMarca());
        return response;
    }
}
