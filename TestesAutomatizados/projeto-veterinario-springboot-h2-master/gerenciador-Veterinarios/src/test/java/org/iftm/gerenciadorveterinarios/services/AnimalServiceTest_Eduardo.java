package org.iftm.gerenciadorveterinarios.services;

import org.iftm.gerenciadorveterinarios.entities.Animal;
import org.iftm.gerenciadorveterinarios.repositories.AnimalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AnimalServiceTest_Eduardo {

    @Mock private AnimalRepository repository;
    @InjectMocks private AnimalService service;

    @Test
    void deveCadastrarAnimalComoInternado() {
        Animal animal = new Animal();
        animal.setEspecie("Cachorro");
        animal.setInternado(false);

        when(repository.save(any(Animal.class))).thenAnswer(i -> i.getArgument(0));
        Animal salvo = service.cadastrar(animal);

        assertTrue(salvo.getInternado());
        verify(repository).save(any(Animal.class));
    }

    @Test
    void deveLancarExcecaoParaEspecieNaoAtendida() {
        Animal animal = new Animal();
        animal.setEspecie("Jacaré");

        assertThrows(IllegalArgumentException.class, () -> service.cadastrar(animal));
        verify(repository, never()).save(any());
    }

    @Test
    void deveDarAltaParaAnimal() {
        Animal animal = new Animal();
        animal.setId(1);
        animal.setInternado(true);

        when(repository.findById(1)).thenReturn(Optional.of(animal));
        when(repository.save(any(Animal.class))).thenAnswer(i -> i.getArgument(0));

        Animal alterado = service.darAlta(1);
        assertFalse(alterado.getInternado());
    }
}