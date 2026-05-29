package br.edu.iftm.tspi.pbackorm.e_commerce.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PedidoDTO {

    private Integer id;

    private LocalDateTime dataPedido;

    @NotBlank(message = "ID do cliente é obrigatório")
    private String idCliente;

    @Valid
    private List<ItemPedidoDTO> itens;
}
