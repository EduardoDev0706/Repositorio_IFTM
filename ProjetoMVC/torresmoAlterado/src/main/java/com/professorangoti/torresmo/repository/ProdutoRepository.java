package com.professorangoti.torresmo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.professorangoti.torresmo.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // NOVO MÉTODO: O Spring Data JPA cria a query SQL automaticamente
    List<Produto> findAllByDestaqueIsTrue();
    
}