package intexsoft.practice.mapper;

import intexsoft.practice.dto.ClientDto;
import intexsoft.practice.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDto toClientDto(Client client);
    Client toEntity(ClientDto clientDto);
}
