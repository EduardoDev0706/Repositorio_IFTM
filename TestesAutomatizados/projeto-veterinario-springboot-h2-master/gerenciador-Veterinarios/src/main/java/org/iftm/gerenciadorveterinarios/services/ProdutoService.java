package org.iftm.gerenciadorveterinarios.services;

import org.iftm.gerenciadorveterinarios.entities.Produto;
import org.iftm.gerenciadorveterinarios.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;

    public Produto cadastrar(Produto produto) {
        if (produto.getPreco() < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo");
        }
        produto.setAtivo(true);
        return repository.save(produto);
    }

    public Produto inativar(Integer id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produto.setAtivo(false);
        return repository.save(produto);
    }
}