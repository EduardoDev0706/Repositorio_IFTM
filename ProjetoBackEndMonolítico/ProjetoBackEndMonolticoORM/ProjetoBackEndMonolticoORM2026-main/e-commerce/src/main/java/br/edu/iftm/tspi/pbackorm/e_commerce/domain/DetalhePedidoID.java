package br.edu.iftm.tspi.pbackorm.e_commerce.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class DetalhePedidoID {

    @Column(name = "PedidoID")
    private Integer pedidoId;

    @Column(name = "ProdutoID")
    private Integer produtoId;

}
