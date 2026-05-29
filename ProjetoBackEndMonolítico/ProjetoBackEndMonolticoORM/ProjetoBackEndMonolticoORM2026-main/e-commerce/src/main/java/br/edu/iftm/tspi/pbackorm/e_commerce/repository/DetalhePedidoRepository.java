package br.edu.iftm.tspi.pbackorm.e_commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.pbackorm.e_commerce.domain.DetalhePedido;
import br.edu.iftm.tspi.pbackorm.e_commerce.domain.DetalhePedidoID;

@Repository
public interface DetalhePedidoRepository extends JpaRepository<DetalhePedido, DetalhePedidoID> {
}
