package org.iftm.gerenciadorveterinarios.services;

import org.iftm.gerenciadorveterinarios.entities.Animal;
import org.iftm.gerenciadorveterinarios.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {
    @Autowired
    private AnimalRepository repository;

    public Animal cadastrar(Animal animal) {
        if (!animal.getEspecie().equalsIgnoreCase("Cachorro") && !animal.getEspecie().equalsIgnoreCase("Gato")) {
            throw new IllegalArgumentException("Espécie não atendida pela clínica");
        }
        animal.setInternado(true);
        return repository.save(animal);
    }

    public Animal darAlta(Integer id) {
        Animal animal = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado"));
        animal.setInternado(false);
        return repository.save(animal);
    }
}