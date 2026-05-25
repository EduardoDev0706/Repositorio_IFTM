package org.iftm.gerenciadorveterinarios.repositories;
import org.iftm.gerenciadorveterinarios.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {}