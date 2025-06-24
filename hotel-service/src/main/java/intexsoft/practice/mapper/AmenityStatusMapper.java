package intexsoft.practice.mapper;

import intexsoft.practice.dto.DictionaryDto;
import intexsoft.practice.dto.UpdateDictionaryDto;
import intexsoft.practice.model.AmenityStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AmenityStatusMapper {
    DictionaryDto toDto(AmenityStatus amenityStatus);
    AmenityStatus toEntity(DictionaryDto amenityStatusDto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(UpdateDictionaryDto updateDictionaryDto, @MappingTarget AmenityStatus amenityStatus);
}
