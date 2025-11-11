package com.eduardoCarmo.condominio.repository;

import com.eduardoCarmo.condominio.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repository para operações de persistência da entidade Proprietario.
 *
 * Herda de JpaRepository<Proprietario, Long> onde:
 * - Proprietario: Tipo da entidade
 * - Long: Tipo da chave primária (@Id)
 *
 * @Repository é opcional quando se estende JpaRepository, mas é boa prática incluir.
 */
@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {
    /**
     * Busca proprietários por nome (busca exata, case-sensitive).
     *
     * Query Method: Spring Data interpreta o nome do método e cria a query SQL.
     * Padrão: findBy + NomeDoAtributo
     */
    List<Proprietario> findByNome(String nome);
    /**
     * Busca proprietários cujo nome contenha o texto fornecido (case-insensitive).
     *
     * Exemplo: findByNomeContainingIgnoreCase("silva")
     * encontra "Silva", "SILVA", "João Silva", etc.
     */
    List<Proprietario> findByNomeContainingIgnoreCase(String nome);
    /**
     * Busca proprietário por telefone (busca exata).
     * Retorna Optional porque pode não encontrar nenhum.
     */
    Optional<Proprietario> findByTelefone(String telefone);
    /**
     * Busca proprietários cujo nome comece com o texto fornecido.
     *
     * Exemplo: findByNomeStartingWith("Mar") encontra "Maria", "Marcos", etc.
     */
    List<Proprietario> findByNomeStartingWith(String prefixo);
    /**
     * Busca proprietários ordenados por nome (ascendente).
     *
     * OrderBy + NomeDoAtributo + Asc/Desc
     */
    List<Proprietario> findAllByOrderByNomeAsc();
    /**
     * Conta quantos proprietários têm um determinado nome.
     */
    long countByNome(String nome);
    /**
     * Verifica se existe algum proprietário com o telefone fornecido.
     * Útil para validar telefones únicos.
     */
    boolean existsByTelefone(String telefone);

    // Consultas JPQL Personalizadas
    /**
     * Busca proprietários que possuem mais de N apartamentos.
     *
     * Usa @Query com JPQL (Java Persistence Query Language).
     * JPQL trabalha com objetos, não com tabelas SQL.
     *
     * @param minApartamentos Número mínimo de apartamentos
     * @return Lista de proprietários
     */
    @Query("SELECT p FROM Proprietario p WHERE SIZE(p.apartamentos) > :minApartamentos")
    List<Proprietario> findProprietariosComMaisDeNApartamentos(@Param("minApartamentos") int minApartamentos);
    
    /**
     * Busca proprietários que possuem ao menos um apartamento vazio.
     *
     * JOIN FETCH: carrega os apartamentos junto com o proprietário (evita lazy loading)
     */
    @Query("SELECT DISTINCT p FROM Proprietario p " +
           "JOIN FETCH p.apartamentos a " +
           "WHERE a.tipoOcupacao = 'Vazio'")
    List<Proprietario> findProprietariosComApartamentosVazios();

    /**
     * Conta total de apartamentos de um proprietário específico.
     *
     * Usa SQL nativo (nativeQuery = true).
     */
    @Query(value = "SELECT COUNT(*) FROM apartamento WHERE proprietario_id = :proprietarioId", nativeQuery = true)
    long countApartamentosByProprietarioId(@Param("proprietarioId") Long proprietarioId);

    /**
     * Busca proprietários por múltiplos critérios.
     *
     * Demonstra uso de múltiplas condições no nome do método.
     */
    List<Proprietario> findByNomeContainingIgnoreCaseAndTelefoneContaining(
        String nome,
        String telefone
    );
}


