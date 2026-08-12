package br.edu.iftm.tspi.pbackorm.catalogo_service.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.iftm.tspi.pbackorm.catalogo_service.domain.Produto;
import br.edu.iftm.tspi.pbackorm.catalogo_service.dto.ProdutoDTO;
import br.edu.iftm.tspi.pbackorm.catalogo_service.dto.UnidadesCompradasDTO;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer> {

    public List<Produto> 
        findByNomeContainingIgnoreCaseAndEstoqueGreaterThanAndPrecoLessThan(
            String nome,Short estoqueMin, Double precoMax);

    @Query("""
              SELECT new br.edu.iftm.tspi.pbackorm.catalogo_service.dto.ProdutoDTO(
                    p.id,p.nome,p.preco,p.estoque,p.caminhoImagem,p.categoria.id)
              FROM Produto p
              WHERE p.preco = (
                    SELECT MAX(p2.preco)
                    FROM Produto p2
                    WHERE p2.categoria.id = p.categoria.id
                    )      
            """)
    public List<ProdutoDTO> buscarProdutosComMaiorPrecoPorCategoria();


    @Query(nativeQuery = true, value = """
            Select p.produtoID,p.produtoNome,p.preco,
                   p.unidadesemestoque,p.imagem,p.categoriaID
            FROM Produtos p
            where p.preco = (
                    Select MAX(p2.preco)
                    From Produtos p2
                    Where p2.categoriaID = p.categoriaID
                      AND p.categoriaID = :categoriaID
                    )
            """)
    public List<ProdutoDTO> buscarProdutosComMaiorPrecoPorCategoriaNativo(
        @Param("categoriaID") Integer categoriaID);

    @Query(nativeQuery=true,value="""
                    Select produtoNome as nomeProduto,
                           sum(dp.quantidade) as unidadesCompradas
                    From detalhes_pedido dp inner join produtos p on dp.produtoid = p.produtoid
                                            inner join pedidos pd on dp.pedidoid = pd.pedidoid
                    Where datapedido between :dataInicio and :dataFim
                    Group by produtoNome
                    Order by unidadesCompradas desc
                    """)
    public List<UnidadesCompradasDTO> findUnidadesCompradasPeriodo(
         @Param("dataInicio") LocalDate inicio,
         @Param("dataFim") LocalDate fim);    

}
