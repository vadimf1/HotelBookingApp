package intexsoft.practice.mapper;

import intexsoft.practice.dto.AddressDto;
import intexsoft.practice.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDto toDto(Address address);
    Address toEntity(AddressDto addressDto);
}
