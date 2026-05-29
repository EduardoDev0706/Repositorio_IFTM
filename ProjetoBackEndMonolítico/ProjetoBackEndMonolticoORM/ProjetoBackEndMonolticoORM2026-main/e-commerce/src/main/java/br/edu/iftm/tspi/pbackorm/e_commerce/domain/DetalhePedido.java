package br.edu.iftm.tspi.pbackorm.e_commerce.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="detalhes_pedido")
@Data
@NoArgsConstructor
public class DetalhePedido {

    @EmbeddedId
    private DetalhePedidoID id;

    @ManyToOne
    @MapsId("pedidoId")
    @JoinColumn(name="PedidoID",nullable=false)
    private Pedido pedido;

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name="ProdutoID",nullable=false)
    private Produto produto;

    @Column(name="precovenda")
    private Double precoVenda;

    @Column(name="quantidade")
    private Short quantidade;

    @Column(name="desconto")
    private Double desconto;
}
