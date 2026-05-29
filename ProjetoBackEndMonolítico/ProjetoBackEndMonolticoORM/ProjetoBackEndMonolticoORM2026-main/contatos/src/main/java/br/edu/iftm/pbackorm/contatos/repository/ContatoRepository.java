package br.edu.iftm.pbackorm.contatos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iftm.pbackorm.contatos.domain.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Integer> {

    List<Contato> findByNomeContainingIgnoreCaseAndEmailContainingIgnoreCaseAndTelefoneContaining(
            String nome, String email, String telefone);
 
}
