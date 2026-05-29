package br.edu.iftm.tspi.pbackorm.e_commerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemPedidoDTO {

    @NotNull(message = "ID do produto é obrigatório")
    private Integer idProduto;

    @Positive(message = "Preço de venda deve ser maior que zero")
    private Double precoVenda;

    @Min(value = 1, message = "Quantidade deve ser maior que zero")
    private Short quantidade;

    @Min(value = 0, message = "Desconto não pode ser negativo")
    private Double desconto;
}
