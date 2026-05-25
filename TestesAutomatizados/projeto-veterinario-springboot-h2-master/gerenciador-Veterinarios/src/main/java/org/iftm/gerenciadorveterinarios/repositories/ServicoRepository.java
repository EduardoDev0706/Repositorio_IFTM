package org.iftm.gerenciadorveterinarios.repositories;
import org.iftm.gerenciadorveterinarios.entities.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {}