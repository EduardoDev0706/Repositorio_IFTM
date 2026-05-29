package br.edu.iftm.tspi.pbackorm.e_commerce.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.edu.iftm.tspi.pbackorm.e_commerce.domain.DetalhePedido;
import br.edu.iftm.tspi.pbackorm.e_commerce.domain.Pedido;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.ItemPedidoDTO;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.PedidoDTO;

@Component
public class PedidoMapper {

    public PedidoDTO toDto(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();

        dto.setId(pedido.getId());
        dto.setDataPedido(pedido.getDataPedido());
        dto.setIdCliente(pedido.getCliente().getId());
        dto.setItens(toItemDtoList(pedido.getDetalhesPedido()));

        return dto;
    }

    public List<PedidoDTO> toDtoList(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(this::toDto)
                .toList();
    }

    public ItemPedidoDTO toItemDto(DetalhePedido detalhe) {
        ItemPedidoDTO dto = new ItemPedidoDTO();

        dto.setIdProduto(detalhe.getProduto().getId());
        dto.setPrecoVenda(detalhe.getPrecoVenda());
        dto.setQuantidade(detalhe.getQuantidade());
        dto.setDesconto(detalhe.getDesconto());

        return dto;
    }

    public List<ItemPedidoDTO> toItemDtoList(List<DetalhePedido> detalhes) {
        if (detalhes == null) {
            return new ArrayList<>();
        }

        return detalhes.stream()
                .map(this::toItemDto)
                .toList();
    }
}
