package br.edu.iftm.tspi.pbackorm.catalogo_service.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.edu.iftm.tspi.pbackorm.catalogo_service.domain.Categoria;
import br.edu.iftm.tspi.pbackorm.catalogo_service.dto.CategoriaDTO;

@Mapper(componentModel="spring")
public interface CategoriaMapper {

    Categoria toEntity(CategoriaDTO categoriaDTO);

    CategoriaDTO toDto(Categoria categoria);

    List<Categoria> toEntityList(List<CategoriaDTO> categoriasDTO);

    List<CategoriaDTO> toDtoList(List<Categoria> categorias);

}
