package com.professorangoti.torresmo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.professorangoti.torresmo.model.Produto;
import com.professorangoti.torresmo.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }
    
    public List<Produto> findDestaques() {
        return produtoRepository.findAllByDestaqueIsTrue();
    }
}