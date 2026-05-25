package org.iftm.gerenciadorveterinarios.services;

import org.iftm.gerenciadorveterinarios.entities.Produto;
import org.iftm.gerenciadorveterinarios.repositories.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest_Mateus {

    @Mock private ProdutoRepository repository;
    @InjectMocks private ProdutoService service;

    @Test
    void deveCadastrarProdutoComoAtivo() {
        Produto produto = new Produto();
        produto.setPreco(10.0);
        produto.setAtivo(false);

        when(repository.save(any(Produto.class))).thenAnswer(i -> i.getArgument(0));
        Produto salvo = service.cadastrar(produto);

        assertTrue(salvo.getAtivo());
        verify(repository).save(any(Produto.class));
    }

    @Test
    void deveLancarExcecaoParaPrecoNegativo() {
        Produto produto = new Produto();
        produto.setPreco(-5.0);

        assertThrows(IllegalArgumentException.class, () -> service.cadastrar(produto));
        verify(repository, never()).save(any());
    }

    @Test
    void deveInativarProduto() {
        Produto produto = new Produto();
        produto.setId(1);
        produto.setAtivo(true);

        when(repository.findById(1)).thenReturn(Optional.of(produto));
        when(repository.save(any(Produto.class))).thenAnswer(i -> i.getArgument(0));

        Produto alterado = service.inativar(1);
        assertFalse(alterado.getAtivo());
    }
}