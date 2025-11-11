package com.eduardoCarmo.condominio.repository;

import com.eduardoCarmo.condominio.model.Apartamento;
import com.eduardoCarmo.condominio.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repository para operações de persistência da entidade Apartamento.
 */
@Repository
public interface ApartamentoRepository extends JpaRepository<Apartamento, Long> {
    /**
     * Busca apartamento por número da porta (deve ser único).
     */
    Optional<Apartamento> findByNumeroPorta(Integer numeroPorta);

    /**
     * Busca apartamentos por tipo de ocupação
     * 
     * Exemplo: findByTipoOcupacao("Vazio") retorna todos apartamentos vazios
     */
    List<Apartamento> findByTipoOcupacao(String tipoOcupacao);

    /**
     * Busca apartamentos por quantidade de quartos.
     */
    List<Apartamento> findByQuantidadeQuartos(Integer quantidade);

    /**
     * Busca apartamentos com quantidade de quartos maior ou igual ao valor.
     */
    List<Apartamento> findByQuantidadeQuartosGreaterThanEqual(Integer quantidade);

    /**
     * Busca todos os apartamentos de um proprietário específico.
     *
     * Nota: Proprietario é um objeto, não um ID.
     */
    List<Apartamento> findByProprietario(Proprietario proprietario);

    /**
     * Busca apartamentos por ID do proprietário.
     *
     * Usa notação de "navegação" através do relacionamento: proprietario.id
     */
    List<Apartamento> findByProprietarioId(Long proprietarioId);

    /**
     * Busca apartamentos por nome do proprietário (case-insensitive).
     */
    List<Apartamento> findByProprietarioNomeContainingIgnoreCase(String nomeProprietario);

    /**
     * Busca apartamentos ordenados por número da porta.
     */
    List<Apartamento> findAllByOrderByNumeroPortaAsc();

    /**
     * Verifica se já existe um apartamento com o número de porta fornecido.
     * Útil para validar unicidade antes de salvar.
     */
    boolean existsByNumeroPorta(Integer numeroPorta);

    /**
     * Conta quantos apartamentos estão vazios.
     */
    long countByTipoOcupacao(String tipoOcupacao);

    // Consulta JPQL Personalizadas
    /**
     * Busca apartamentos por múltiplos critérios.
     *
     * @param tipoOcupacao Tipo de ocupação (pode ser null para ignorar)
     * @param minQuartos Quantidade mínima de quartos
     * @return Lista de apartamentos que atendem os critérios
     */
    @Query("SELECT a FROM Apartamento a WHERE " +
           "(:tipoOcupacao IS NULL OR a.tipoOcupacao = :tipoOcupacao) AND " +
           "a.quantidadeQuartos >= :minQuartos")
    List<Apartamento> findByFiltros(
        @Param("tipoOcupacao") String tipoOcupacao,
        @Param("minQuartos") Integer minQuartos
    );

    /**
     * Busca apartamentos com seus proprietários (JOIN FETCH para evitar N+1 queries).
     *
     * Sempre carrega o proprietário junto, mais eficiente quando você sabe que vai precisar.
     */
    @Query("SELECT a FROM Apartamento a JOIN FETCH a.proprietario")
    List<Apartamento> findAllWithProprietarios();

    /**
     * Estatística: Média de quartos por tipo de ocupação.
     *
     * Exemplo de agregação usando JPQL.
     */
    @Query("SELECT AVG(a.quantidadeQuartos) FROM Apartamento a WHERE a.tipoOcupacao = :tipo")
    Double calcularMediaQuartosPorTipo(@Param("tipo") String tipo);

    /**
     * Busca apartamentos de proprietários que moram em mais de um apartamento.
     */
    @Query("SELECT a FROM Apartamento a WHERE " +
           "(SELECT COUNT(a2) FROM Apartamento a2 WHERE a2.proprietario = a.proprietario) > 1")
    List<Apartamento> findApartamentosDeProprietariosComMultiplosApartamentos();


    // ==================== CONSULTAS SQL NATIVAS ====================

    /**
     * Estatística usando SQL nativo: Total de apartamentos por tipo de ocupação.
     *
     * Retorna um resultado customizado (Object[]).
     */
    @Query(value = "SELECT tipo_ocupacao, COUNT(*) as total " +
                   "FROM apartamento " +
                   "GROUP BY tipo_ocupacao " +
                   "ORDER BY total DESC",
           nativeQuery = true)
    List<Object[]> estatisticasPorTipoOcupacao();
}
