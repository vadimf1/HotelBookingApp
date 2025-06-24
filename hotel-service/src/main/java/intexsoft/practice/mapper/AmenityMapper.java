package intexsoft.practice.mapper;

import intexsoft.practice.dto.AddAmenityDto;
import intexsoft.practice.dto.ResponseAmenityDto;
import intexsoft.practice.dto.UpdateAmenityDto;
import intexsoft.practice.model.Amenity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AmenityMapper {
    ResponseAmenityDto toDto(Amenity amenity);
    Amenity toEntity(AddAmenityDto addAmenityDto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(UpdateAmenityDto updateAmenityDto, @MappingTarget Amenity amenity);
}
