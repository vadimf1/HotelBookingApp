package intexsoft.practice.booking_service.mapper;

import intexsoft.practice.booking_service.model.RoomBooking;
import intexsoft.practice.booking_service_kafka_dto.dto.KafkaBookingEventDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface KafkaEventMapper {

    @Mapping(target = "eventType", constant = "BOOKING_CREATED")
    KafkaBookingEventDTO toKafkaEventDTOWithCreatedType(RoomBooking roomBooking);

    @Mapping(target = "eventType", constant = "BOOKING_CANCELLED")
    KafkaBookingEventDTO toKafkaEventDTOWithCancelledType(RoomBooking roomBooking);
}
