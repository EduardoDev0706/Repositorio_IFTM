package br.edu.iftm.tspi.pbackorm.e_commerce.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import br.edu.iftm.tspi.pbackorm.e_commerce.domain.Cliente;
import br.edu.iftm.tspi.pbackorm.e_commerce.dto.ClienteDTO;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toEntity(ClienteDTO dto);

    ClienteDTO toDto(Cliente entity);

    List<ClienteDTO> toDtoList(List<Cliente> clientes);
}
