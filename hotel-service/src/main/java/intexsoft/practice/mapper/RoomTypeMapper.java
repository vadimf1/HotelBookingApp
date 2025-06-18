package intexsoft.practice.mapper;

import intexsoft.practice.dto.RoomTypeDto;
import intexsoft.practice.dto.UpdateRoomTypeDto;
import intexsoft.practice.model.RoomType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomTypeMapper {
    RoomTypeDto toDto(RoomType roomType);
    RoomType toEntity(RoomTypeDto roomTypeDto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(UpdateRoomTypeDto updateRoomTypeDto, @MappingTarget RoomType roomType);
}