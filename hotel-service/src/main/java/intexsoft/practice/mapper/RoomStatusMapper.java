package intexsoft.practice.mapper;

import intexsoft.practice.dto.DictionaryDto;
import intexsoft.practice.dto.UpdateDictionaryDto;
import intexsoft.practice.model.RoomStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomStatusMapper {
    DictionaryDto toDto(RoomStatus roomStatus);
    RoomStatus toEntity(DictionaryDto roomStatusDto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(UpdateDictionaryDto updateDictionaryDto, @MappingTarget RoomStatus roomStatus);
}