package org.iftm.gerenciadorveterinarios.repositories;
import org.iftm.gerenciadorveterinarios.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {}