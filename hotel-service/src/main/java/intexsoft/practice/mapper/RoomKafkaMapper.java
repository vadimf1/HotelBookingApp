package intexsoft.practice.mapper;

import intexsoft.practice.dto.KafkaResponseRoomDto;
import intexsoft.practice.model.Room;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomKafkaMapper {
    KafkaResponseRoomDto toDto(Room room);
}
