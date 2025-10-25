package com.professorangoti.torresmo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long idProduto;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "preco", precision = 10, scale = 2)
    private Double preco;

    @Column(name = "tamanho", length = 50)
    private String tamanho;

    @Column(name = "disponivel")
    private Boolean disponivel;

    @Column(name = "destaque", nullable = false)
    private Boolean destaque = false;
}
