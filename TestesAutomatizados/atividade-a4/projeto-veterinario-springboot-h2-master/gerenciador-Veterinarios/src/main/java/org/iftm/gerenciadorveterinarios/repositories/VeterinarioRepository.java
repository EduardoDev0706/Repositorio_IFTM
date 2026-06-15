package org.iftm.gerenciadorveterinarios.repositories;

import java.util.List;

import org.iftm.gerenciadorveterinarios.entities.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VeterinarioRepository extends JpaRepository<Veterinario, Integer> {

   public List<Veterinario> findByNomeContains(String nome);
}
