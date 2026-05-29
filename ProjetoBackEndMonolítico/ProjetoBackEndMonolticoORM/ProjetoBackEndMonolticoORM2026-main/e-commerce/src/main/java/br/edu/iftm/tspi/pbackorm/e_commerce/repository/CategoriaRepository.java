package br.edu.iftm.tspi.pbackorm.e_commerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.pbackorm.e_commerce.domain.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {
    List<Categoria> findByNomeContainingIgnoreCase(String nome);

}
