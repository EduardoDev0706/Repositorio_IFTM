package br.edu.iftm.tspi.pbackorm.e_commerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.tspi.pbackorm.e_commerce.domain.Categoria;
import br.edu.iftm.tspi.pbackorm.e_commerce.domain.Produto;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.ProdutoDTO;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.mapper.ProdutoMapper;
import br.edu.iftm.tspi.pbackorm.e_commerce.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/produtos")
@AllArgsConstructor
public class ProdutoController {

    private final ProdutoRepository repository;

    private final ProdutoMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> 
                listar(@RequestParam(required = false) String nome,
                       @RequestParam(required = false) Short estoque,
                       @RequestParam(required = false) Double preco) {
         nome = (nome == null)?"" : nome;
         estoque = (estoque == null)? Short.MIN_VALUE : estoque;
         preco = (preco == null)? Double.MAX_VALUE : preco;
         List<Produto> produtos = repository.
               findByNomeContainingIgnoreCaseAndEstoqueGreaterThanAndPrecoLessThan(
                              nome, estoque, preco);
         return ResponseEntity.ok().body(mapper.toDtoList(produtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable Integer id) {
        Produto produto = repository.findById(id)
                                    .orElseThrow(() -> new EntityNotFoundException(
                                        "Produto de ID "+id+" não encontrado"
                                    ));
        return ResponseEntity.ok().body(mapper.toDto(produto));
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> novo(@Valid @RequestBody ProdutoDTO produtoNovoDto) {
        Produto produtoEntidade = mapper.toEntity(produtoNovoDto);
        Produto produtoSalvo = repository.save(produtoEntidade);
        ProdutoDTO produtoSalvoDto = mapper.toDto(produtoSalvo);
        return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(produtoSalvoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizar(@PathVariable Integer id, 
                                      @Valid @RequestBody ProdutoDTO produtoDTO) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Produto de ID "+id+" não encontrado");
        }
        Produto produtoAtualizar = mapper.toEntity(produtoDTO);
        produtoAtualizar.setId(id);
        Produto produtoAtualizado = repository.save(produtoAtualizar);
        return ResponseEntity.ok().body(mapper.toDto(produtoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        Produto produto = repository.findById(id)
                                        .orElseThrow(() -> new EntityNotFoundException(
                                            "Produto de ID "+id+" não encontrado"
                                        ));
        repository.delete(produto);
        return ResponseEntity.noContent().build();
    }

    
}
