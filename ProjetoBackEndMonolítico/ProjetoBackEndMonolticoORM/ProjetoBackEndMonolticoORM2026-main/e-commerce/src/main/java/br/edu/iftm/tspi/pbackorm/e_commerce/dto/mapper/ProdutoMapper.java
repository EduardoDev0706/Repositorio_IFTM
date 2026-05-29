package br.edu.iftm.tspi.pbackorm.e_commerce.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.edu.iftm.tspi.pbackorm.e_commerce.domain.Produto;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.ProdutoDTO;

@Mapper(componentModel="spring")
public interface ProdutoMapper {

    @Mapping(source = "idCategoria", target = "categoria.id")
    Produto toEntity(ProdutoDTO dto);

    @Mapping(source = "categoria.id", target = "idCategoria")
    ProdutoDTO toDto(Produto entity);

    List<ProdutoDTO> toDtoList(List<Produto> produtos);

    List<Produto> toEntityList(List<ProdutoDTO> dtos);

}
