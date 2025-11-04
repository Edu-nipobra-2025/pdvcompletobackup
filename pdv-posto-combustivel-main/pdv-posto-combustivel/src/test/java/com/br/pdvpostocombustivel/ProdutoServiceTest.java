package com.br.pdvpostocombustivel;

import com.br.pdvpostocombustivel.api.produto.ProdutoService;
import com.br.pdvpostocombustivel.api.produto.dto.ProdutoRequest;
import com.br.pdvpostocombustivel.api.produto.dto.ProdutoResponse;
import com.br.pdvpostocombustivel.domain.entity.Produto;
import com.br.pdvpostocombustivel.domain.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve criar produto com sucesso")
    void deveCriarProdutoComSucesso() {
        // Arrange (Preparar)
        ProdutoRequest request = new ProdutoRequest();
        request.setNome("Gasolina Comum");
        request.setReferencia("GAS-001");
        request.setFornecedor("Petrobras");
        request.setCategoria("Combustível");
        request.setMarca("BR");

        Produto produtoSalvo = new Produto();
        produtoSalvo.setId(1L);
        produtoSalvo.setNome("Gasolina Comum");
        produtoSalvo.setReferencia("GAS-001");

        when(produtoRepository.save(any(Produto.class))).thenReturn(produtoSalvo);

        // Act (Agir)
        ProdutoResponse response = produtoService.criar(request);

        // Assert (Verificar)
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Gasolina Comum", response.getNome());
        verify(produtoRepository, times(1)).save(any(Produto.class));
    }

    @Test
    @DisplayName("Deve buscar produto por ID com sucesso")
    void deveBuscarProdutoPorIdComSucesso() {
        // Arrange
        Produto produto = new Produto();
        produto.setId(1L);
        produto.setNome("Diesel S10");

        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        // Act
        ProdutoResponse response = produtoService.buscarPorId(1L);

        // Assert
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Diesel S10", response.getNome());
    }

    @Test
    @DisplayName("Deve lançar exceção quando produto não for encontrado")
    void deveLancarExcecaoQuandoProdutoNaoEncontrado() {
        // Arrange
        when(produtoRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> {
            produtoService.buscarPorId(999L);
        });
    }

    @Test
    @DisplayName("Deve deletar produto com sucesso")
    void deveDeletarProdutoComSucesso() {
        // Arrange
        when(produtoRepository.existsById(1L)).thenReturn(true);
        doNothing().when(produtoRepository).deleteById(1L);

        // Act
        produtoService.deletar(1L);

        // Assert
        verify(produtoRepository, times(1)).existsById(1L);
        verify(produtoRepository, times(1)).deleteById(1L);
    }
}
