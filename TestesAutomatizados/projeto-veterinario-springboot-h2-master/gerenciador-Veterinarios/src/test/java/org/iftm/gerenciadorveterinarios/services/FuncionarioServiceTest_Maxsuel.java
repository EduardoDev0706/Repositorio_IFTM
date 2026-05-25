package org.iftm.gerenciadorveterinarios.services;

import org.iftm.gerenciadorveterinarios.entities.Funcionario;
import org.iftm.gerenciadorveterinarios.repositories.FuncionarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FuncionarioServiceTest_Maxsuel {

    @Mock private FuncionarioRepository repository;
    @InjectMocks private FuncionarioService service;

    @Test
    void deveCadastrarFuncionarioSemFerias() {
        Funcionario func = new Funcionario();
        func.setSalario(2000.0);
        func.setEmFerias(true);

        when(repository.save(any(Funcionario.class))).thenAnswer(i -> i.getArgument(0));
        Funcionario salvo = service.cadastrar(func);

        assertFalse(salvo.getEmFerias());
        verify(repository).save(any(Funcionario.class));
    }

    @Test
    void deveLancarExcecaoParaSalarioAbaixoDoMinimo() {
        Funcionario func = new Funcionario();
        func.setSalario(1000.0);

        assertThrows(IllegalArgumentException.class, () -> service.cadastrar(func));
        verify(repository, never()).save(any());
    }

    @Test
    void deveConcederFerias() {
        Funcionario func = new Funcionario();
        func.setId(1);
        func.setEmFerias(false);

        when(repository.findById(1)).thenReturn(Optional.of(func));
        when(repository.save(any(Funcionario.class))).thenAnswer(i -> i.getArgument(0));

        Funcionario alterado = service.concederFerias(1);
        assertTrue(alterado.getEmFerias());
    }
}