package br.edu.iftm.tspi.pbackorm.e_commerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.pbackorm.e_commerce.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer> {

    public List<Produto> 
        findByNomeContainingIgnoreCaseAndEstoqueGreaterThanAndPrecoLessThan(
            String nome,Short estoqueMin, Double precoMax);

}
