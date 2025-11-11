package com.eduardoCarmo.condominio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidade JPA que representa um proprietário no sistema de condomínio.
 * Um proprietário pode possuir múltiplos apartamentos (relacionamento 1:N).
 */
@Entity
@Table(name = "proprietario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proprietario {

    /**
     * Chave primária gerada automaticamente pelo banco de dados.
     * A estratégia IDENTITY é ideal para H2, MySQL e PostgreSQL.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proprietario")
    private Long id;

    /**
     * Nome do proprietário.
     * Validações:
     * - Não pode ser nulo ou vazio
     * - Deve ter entre 3 e 100 caracteres
     */
    @NotBlank(message = "O nome do proprietário é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nome;

    /**
     * Telefone de contato do proprietário.
     * Validações:
     * - Não pode ser nulo ou vazio
     * - Deve seguir padrão brasileiro: (XX) XXXXX-XXXX ou (XX) XXXX-XXXX
     */
    @NotBlank(message = "O telefone é obrigatório")
    @Pattern(
        regexp = "\\(\\d{2}\\)\\s?\\d{4,5}-\\d{4}",
        message = "Telefone deve estar no formato (XX) XXXXX-XXXX"
    )
    @Column(nullable = false, length = 20)
    private String telefone;

    /**
     * Relacionamento bidirecional 1:N com Apartamento.
     * Um proprietário pode ter vários apartamentos.
     *
     * mappedBy: indica que o relacionamento é gerenciado pelo atributo
     * "proprietario" na classe Apartamento
     * cascade: operações em cascata (salvar proprietário salva apartamentos)
     * orphanRemoval: remove apartamentos órfãos (sem proprietário)
     * fetch: LAZY = carrega apartamentos apenas quando acessados
     */
    @OneToMany(
        mappedBy = "proprietario",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private List<Apartamento> apartamentos = new ArrayList<>();

    /**
     * Método auxiliar para adicionar um apartamento à lista.
     * Mantém sincronização bidirecional do relacionamento.
     */
    public void adicionarApartamento(Apartamento apartamento) {
        apartamentos.add(apartamento);
        apartamento.setProprietario(this);
    }

    /**
     * Método auxiliar para remover um apartamento da lista.
     * Mantém sincronização bidirecional do relacionamento.
     */
    public void removerApartamento(Apartamento apartamento) {
        apartamentos.remove(apartamento);
        apartamento.setProprietario(null);
    }
}