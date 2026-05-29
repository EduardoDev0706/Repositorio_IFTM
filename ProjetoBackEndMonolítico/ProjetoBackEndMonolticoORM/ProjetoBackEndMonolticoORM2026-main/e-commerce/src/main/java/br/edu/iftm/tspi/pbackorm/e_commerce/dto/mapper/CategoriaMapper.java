package br.edu.iftm.tspi.pbackorm.e_commerce.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.edu.iftm.tspi.pbackorm.e_commerce.domain.Categoria;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.CategoriaDTO;

@Mapper(componentModel="spring")
public interface CategoriaMapper {

    Categoria toEntity(CategoriaDTO categoriaDTO);

    CategoriaDTO toDto(Categoria categoria);

    List<Categoria> toEntityList(List<CategoriaDTO> categoriasDTO);

    List<CategoriaDTO> toDtoList(List<Categoria> categorias);

}
