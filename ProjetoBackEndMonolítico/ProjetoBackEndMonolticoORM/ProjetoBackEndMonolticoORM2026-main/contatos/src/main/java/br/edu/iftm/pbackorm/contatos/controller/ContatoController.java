package br.edu.iftm.pbackorm.contatos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.pbackorm.contatos.domain.Contato;
import br.edu.iftm.pbackorm.contatos.repository.ContatoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/contatos")
public class ContatoController {

    private final ContatoRepository repository;

    public ContatoController(ContatoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> buscarPorId(@PathVariable Integer id) {
        Contato contato = repository.findById(id)
                            .orElseThrow(() -> new EntityNotFoundException
                                ("Contato de ID "+id+" não encontrado"));
        return ResponseEntity.ok().body(contato);   
    }
 
    @GetMapping
    public List<Contato> listar(
        @RequestParam(required = false) String nome,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) String telefone
    ) {
        nome = (nome == null) ? "" : nome;
        email = (email == null) ? "" : email;
        telefone = (telefone == null) ? "" : telefone;

        return repository
                .findByNomeContainingIgnoreCaseAndEmailContainingIgnoreCaseAndTelefoneContaining(
                    nome, email, telefone);
    }

    @PostMapping
    public ResponseEntity<Contato> novo(@Valid @RequestBody Contato novoContato) {
        Contato contato = repository.save(novoContato);
        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(contato);
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<Contato> atualizar(@PathVariable Integer id,
                                       @Valid @RequestBody Contato contatoAtualizar) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Contato de ID "+id+" não encontrado");
        }
        contatoAtualizar.setCodigo(id);
        Contato contatoAtualizado = repository.save(contatoAtualizar);
        return ResponseEntity.ok(contatoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        Contato contato = repository.findById(id)
                            .orElseThrow(() -> new EntityNotFoundException
                                ("Contato de ID "+id+" não encontrado"));
        repository.delete(contato);
        return ResponseEntity.noContent().build();   
    }
 
    /* 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();   
    }
*/
}
