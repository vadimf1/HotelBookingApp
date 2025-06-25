package intexsoft.practice.mapper;

import intexsoft.practice.dto.DictionaryDto;
import intexsoft.practice.dto.UpdateDictionaryDto;
import intexsoft.practice.model.HotelStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface HotelStatusMapper {
    DictionaryDto toDto(HotelStatus hotelStatus);
    HotelStatus toEntity(DictionaryDto dictionaryDto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(UpdateDictionaryDto updateDictionaryDto, @MappingTarget HotelStatus hotelStatus);

}
