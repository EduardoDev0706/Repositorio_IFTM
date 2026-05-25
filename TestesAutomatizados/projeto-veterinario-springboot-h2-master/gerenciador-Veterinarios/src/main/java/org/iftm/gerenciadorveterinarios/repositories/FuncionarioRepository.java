package org.iftm.gerenciadorveterinarios.repositories;
import org.iftm.gerenciadorveterinarios.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {}