package intexsoft.practice.mapper;

import intexsoft.practice.dto.AddRoomDto;
import intexsoft.practice.dto.ResponseRoomDto;
import intexsoft.practice.dto.UpdateRoomDto;
import intexsoft.practice.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {HotelMapper.class, RoomStatusMapper.class, RoomTypeMapper.class})
public interface RoomMapper {
    ResponseRoomDto toDto(Room room);
    Room toEntity(AddRoomDto addRoomDto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(UpdateRoomDto updateRoomDto, @MappingTarget Room room);
}
