package intexsoft.practice.booking_service.mapper;

import intexsoft.practice.booking_service.model.Room;
import intexsoft.practice.booking_service_kafka_dto.dto.KafkaResponseRoomDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    Room toEntity(KafkaResponseRoomDTO responseDTO);
}
