package org.iftm.gerenciadorveterinarios.services;

import org.iftm.gerenciadorveterinarios.entities.Servico;
import org.iftm.gerenciadorveterinarios.repositories.ServicoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServicoServiceTest_Gabriel {

    @Mock private ServicoRepository repository;
    @InjectMocks private ServicoService service;

    @Test
    void deveCadastrarServicoComoDisponivel() {
        Servico servico = new Servico();
        servico.setTempoMinutos(30);
        servico.setDisponivel(false);

        when(repository.save(any(Servico.class))).thenAnswer(i -> i.getArgument(0));
        Servico salvo = service.cadastrar(servico);

        assertTrue(salvo.getDisponivel());
        verify(repository).save(any(Servico.class));
    }

    @Test
    void deveLancarExcecaoParaTempoZeradoOuNegativo() {
        Servico servico = new Servico();
        servico.setTempoMinutos(0);

        assertThrows(IllegalArgumentException.class, () -> service.cadastrar(servico));
        verify(repository, never()).save(any());
    }

    @Test
    void deveSuspenderServico() {
        Servico servico = new Servico();
        servico.setId(1);
        servico.setDisponivel(true);

        when(repository.findById(1)).thenReturn(Optional.of(servico));
        when(repository.save(any(Servico.class))).thenAnswer(i -> i.getArgument(0));

        Servico alterado = service.suspender(1);
        assertFalse(alterado.getDisponivel());
    }
}