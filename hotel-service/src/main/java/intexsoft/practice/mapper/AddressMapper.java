package intexsoft.practice.mapper;

import intexsoft.practice.dto.AddressDto;
import intexsoft.practice.dto.UpdateAddressDto;
import intexsoft.practice.model.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AddressMapper {
    AddressDto toDto(Address address);
    Address toEntity(AddressDto addressDto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(UpdateAddressDto updateAddressDto, @MappingTarget Address address);
}
