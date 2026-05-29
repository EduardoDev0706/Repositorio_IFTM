package br.edu.iftm.tspi.pbackorm.e_commerce.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.iftm.tspi.pbackorm.e_commerce.domain.Cliente;
import br.edu.iftm.tspi.pbackorm.e_commerce.domain.DetalhePedido;
import br.edu.iftm.tspi.pbackorm.e_commerce.domain.DetalhePedidoID;
import br.edu.iftm.tspi.pbackorm.e_commerce.domain.Pedido;
import br.edu.iftm.tspi.pbackorm.e_commerce.domain.Produto;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.ItemPedidoDTO;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.PedidoDTO;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.mapper.PedidoMapper;
import br.edu.iftm.tspi.pbackorm.e_commerce.repository.ClienteRepository;
import br.edu.iftm.tspi.pbackorm.e_commerce.repository.DetalhePedidoRepository;
import br.edu.iftm.tspi.pbackorm.e_commerce.repository.PedidoRepository;
import br.edu.iftm.tspi.pbackorm.e_commerce.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/pedidos")
@AllArgsConstructor
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final DetalhePedidoRepository detalhePedidoRepository;
    private final PedidoMapper mapper;

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listar() {
        return ResponseEntity.ok(mapper.toDtoList(pedidoRepository.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> buscarPorId(@PathVariable Integer id) {
        Pedido pedido = buscarPedido(id);
        return ResponseEntity.ok(mapper.toDto(pedido));
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> novo(@Valid @RequestBody PedidoDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cliente de ID " + dto.getIdCliente() + " não encontrado"
                ));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataPedido(dto.getDataPedido() == null ? LocalDateTime.now() : dto.getDataPedido());
        pedido.setDetalhesPedido(new ArrayList<>());

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        if (dto.getItens() != null) {
            for (ItemPedidoDTO itemDto : dto.getItens()) {
                DetalhePedido detalhe = criarDetalhePedido(pedidoSalvo, itemDto);
                detalhePedidoRepository.save(detalhe);
                pedidoSalvo.getDetalhesPedido().add(detalhe);
            }
        }

        Pedido pedidoCompleto = buscarPedido(pedidoSalvo.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toDto(pedidoCompleto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody PedidoDTO dto) {

        Pedido pedido = buscarPedido(id);

        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cliente de ID " + dto.getIdCliente() + " não encontrado"
                ));

        pedido.setCliente(cliente);
        pedido.setDataPedido(dto.getDataPedido());

        Pedido pedidoAtualizado = pedidoRepository.save(pedido);

        return ResponseEntity.ok(mapper.toDto(pedidoAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        Pedido pedido = buscarPedido(id);
        pedidoRepository.delete(pedido);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{pedidoId}/itens")
    public ResponseEntity<List<ItemPedidoDTO>> listarItens(@PathVariable Integer pedidoId) {
        Pedido pedido = buscarPedido(pedidoId);
        return ResponseEntity.ok(mapper.toItemDtoList(pedido.getDetalhesPedido()));
    }

    @PostMapping("/{pedidoId}/itens")
    public ResponseEntity<ItemPedidoDTO> adicionarItem(
            @PathVariable Integer pedidoId,
            @Valid @RequestBody ItemPedidoDTO dto) {

        Pedido pedido = buscarPedido(pedidoId);
        DetalhePedido detalhe = criarDetalhePedido(pedido, dto);

        detalhePedidoRepository.save(detalhe);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.toItemDto(detalhe));
    }

    @PutMapping("/{pedidoId}/itens/{index}")
    public ResponseEntity<ItemPedidoDTO> atualizarItem(
            @PathVariable Integer pedidoId,
            @PathVariable Integer index,
            @Valid @RequestBody ItemPedidoDTO dto) {

        Pedido pedido = buscarPedido(pedidoId);
        DetalhePedido detalhe = buscarItemPorIndex(pedido, index);

        Produto produto = produtoRepository.findById(dto.getIdProduto())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Produto de ID " + dto.getIdProduto() + " não encontrado"
                ));

        DetalhePedidoID idAntigo = detalhe.getId();

        detalhePedidoRepository.delete(detalhe);

        DetalhePedido novoDetalhe = new DetalhePedido();

        DetalhePedidoID novoId = new DetalhePedidoID();
        novoId.setPedidoId(pedido.getId());
        novoId.setProdutoId(produto.getId());

        novoDetalhe.setId(novoId);
        novoDetalhe.setPedido(pedido);
        novoDetalhe.setProduto(produto);
        novoDetalhe.setPrecoVenda(dto.getPrecoVenda());
        novoDetalhe.setQuantidade(dto.getQuantidade());
        novoDetalhe.setDesconto(dto.getDesconto());

        detalhePedidoRepository.save(novoDetalhe);

        return ResponseEntity.ok(mapper.toItemDto(novoDetalhe));
    }

    @DeleteMapping("/{pedidoId}/itens/{index}")
    public ResponseEntity<Void> removerItem(
            @PathVariable Integer pedidoId,
            @PathVariable Integer index) {

        Pedido pedido = buscarPedido(pedidoId);
        DetalhePedido detalhe = buscarItemPorIndex(pedido, index);

        detalhePedidoRepository.delete(detalhe);

        return ResponseEntity.noContent().build();
    }

    private Pedido buscarPedido(Integer id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Pedido de ID " + id + " não encontrado"
                ));
    }

    private DetalhePedido buscarItemPorIndex(Pedido pedido, Integer index) {
        List<DetalhePedido> itens = pedido.getDetalhesPedido();

        if (itens == null || index < 0 || index >= itens.size()) {
            throw new EntityNotFoundException("Item de índice " + index + " não encontrado");
        }

        return itens.get(index);
    }

    private DetalhePedido criarDetalhePedido(Pedido pedido, ItemPedidoDTO dto) {
        Produto produto = produtoRepository.findById(dto.getIdProduto())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Produto de ID " + dto.getIdProduto() + " não encontrado"
                ));

        DetalhePedidoID id = new DetalhePedidoID();
        id.setPedidoId(pedido.getId());
        id.setProdutoId(produto.getId());

        DetalhePedido detalhe = new DetalhePedido();
        detalhe.setId(id);
        detalhe.setPedido(pedido);
        detalhe.setProduto(produto);
        detalhe.setPrecoVenda(dto.getPrecoVenda());
        detalhe.setQuantidade(dto.getQuantidade());
        detalhe.setDesconto(dto.getDesconto());

        return detalhe;
    }
}
