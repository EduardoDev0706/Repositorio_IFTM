package com.eduardoCarmo.condominio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade JPA que representa um apartamento no sistema de condomínio.
 * Cada apartamento pertence a exatamente um proprietário (relacionamento N:1).
 */
@Entity
@Table(name = "apartamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Apartamento {

    /**
     * Chave primária gerada automaticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_apartamento")
    private Long id;

    /**
     * Número da porta do apartamento.
     * Validações:
     * - Não pode ser nulo
     * - Deve ser um número positivo
     */
    @NotNull(message = "O número da porta é obrigatório")
    @Min(value = 1, message = "O número da porta deve ser maior que zero")
    @Column(name = "numero_porta", nullable = false)
    private Integer numeroPorta;

    /**
     * Quantidade de quartos do apartamento.
     * Validações:
     * - Não pode ser nulo
     * - Deve ser entre 1 e 10
     */
    @NotNull(message = "A quantidade de quartos é obrigatória")
    @Min(value = 1, message = "Deve haver pelo menos 1 quarto")
    @Max(value = 10, message = "Máximo de 10 quartos permitido")
    @Column(name = "quantidade_quartos", nullable = false)
    private Integer quantidadeQuartos;

    /**
     * Tipo de ocupação do apartamento.
     * Valores possíveis: "Proprietário", "Inquilino", "Vazio"
     */
    @NotBlank(message = "O tipo de ocupação é obrigatório")
    @Pattern(
        regexp = "Proprietário|Inquilino|Vazio",
        message = "Tipo de ocupação deve ser: Proprietário, Inquilino ou Vazio"
    )
    @Column(name = "tipo_ocupacao", nullable = false, length = 20)
    private String tipoOcupacao;

    /**
     * Relacionamento N:1 com Proprietario.
     * Vários apartamentos pertencem a um proprietário.
     *
     * fetch: EAGER = sempre carrega o proprietário junto com o apartamento
     * optional: false = todo apartamento DEVE ter um proprietário
     *
     * @JoinColumn especifica o nome da coluna de chave estrangeira
     */
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(
        name = "proprietario_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_apartamento_proprietario")
    )
    @NotNull(message = "O proprietário é obrigatório")
    private Proprietario proprietario;

    /**
     * Construtor auxiliar sem o ID (útil para criação de novos apartamentos).
     */
    public Apartamento(Integer numeroPorta, Integer quantidadeQuartos,
                      String tipoOcupacao, Proprietario proprietario) {
        this.numeroPorta = numeroPorta;
        this.quantidadeQuartos = quantidadeQuartos;
        this.tipoOcupacao = tipoOcupacao;
        this.proprietario = proprietario;
    }
}