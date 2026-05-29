package br.edu.iftm.tspi.pbackorm.e_commerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.iftm.tspi.pbackorm.e_commerce.domain.Cliente;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.ClienteDTO;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.PedidoDTO;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.mapper.ClienteMapper;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.mapper.PedidoMapper;
import br.edu.iftm.tspi.pbackorm.e_commerce.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteController {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;
    private final PedidoMapper pedidoMapper;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listar() {
        List<Cliente> clientes = repository.findAll();
        return ResponseEntity.ok(mapper.toDtoList(clientes));
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable String clienteId) {
        Cliente cliente = repository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cliente de ID " + clienteId + " não encontrado"
                ));

        return ResponseEntity.ok(mapper.toDto(cliente));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> novo(@Valid @RequestBody ClienteDTO dto) {
        Cliente cliente = mapper.toEntity(dto);
        Cliente clienteSalvo = repository.save(cliente);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toDto(clienteSalvo));
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<ClienteDTO> atualizar(
            @PathVariable String clienteId,
            @Valid @RequestBody ClienteDTO dto) {

        if (!repository.existsById(clienteId)) {
            throw new EntityNotFoundException("Cliente de ID " + clienteId + " não encontrado");
        }

        Cliente cliente = mapper.toEntity(dto);
        cliente.setId(clienteId);

        Cliente clienteAtualizado = repository.save(cliente);

        return ResponseEntity.ok(mapper.toDto(clienteAtualizado));
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> deletar(@PathVariable String clienteId) {
        Cliente cliente = repository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cliente de ID " + clienteId + " não encontrado"
                ));

        repository.delete(cliente);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{clienteId}/pedidos")
    public ResponseEntity<List<PedidoDTO>> listarPedidosDoCliente(@PathVariable String clienteId) {
        Cliente cliente = repository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cliente de ID " + clienteId + " não encontrado"
                ));

        return ResponseEntity.ok(pedidoMapper.toDtoList(cliente.getPedidos()));
    }
}

