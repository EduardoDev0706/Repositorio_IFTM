package br.edu.iftm.tspi.pbackorm.e_commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.pbackorm.e_commerce.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
