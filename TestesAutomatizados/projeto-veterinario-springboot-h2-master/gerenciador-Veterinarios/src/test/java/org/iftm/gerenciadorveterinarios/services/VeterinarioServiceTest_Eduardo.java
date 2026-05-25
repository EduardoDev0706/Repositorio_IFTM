package org.iftm.gerenciadorveterinarios.services; 
import org.iftm.gerenciadorveterinarios.entities.Veterinario;
import org.iftm.gerenciadorveterinarios.repositories.VeterinarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VeterinarioServiceTest_Eduardo { 

    @Mock
    private VeterinarioRepository repositorio;

    @InjectMocks
    private VeterinarioService service;

    // Desafio 1: Testando buscas com Listas 
    @Test
    void deveRetornarListaComDoisVeterinariosAoBuscarPorParteDoNome() {
        // Arrange
        Veterinario v1 = new Veterinario();
        Veterinario v2 = new Veterinario();
        List<Veterinario> listaSimulada = Arrays.asList(v1, v2);
        
        // Treinando o mock com o método exato do repository 
        when(repositorio.findByNomeContains("Silva")).thenReturn(listaSimulada);

        // Act
        List<Veterinario> resultado = service.buscaVeterinariosComParteNome("Silva");

        // Assert 
        assertEquals(2, resultado.size());
        verify(repositorio).findByNomeContains("Silva");
    }

    // Desafio 2: Protegendo a Exclusão 
    @Test
    void deveLancarExcecaoAoApagarQuandoIdNaoExistir() {
        // Arrange: ID inexistente e mock retornando Optional vazio 
        Integer idInexistente = 99;
        when(repositorio.findById(idInexistente)).thenReturn(Optional.empty());

        // Act & Assert: Verifica a exceção
        assertThrows(RuntimeException.class, () -> {
            service.apagar(idInexistente);
        });

        // Assert: Garante que o delete nunca foi chamado 
        verify(repositorio, never()).delete(any());
    }
}