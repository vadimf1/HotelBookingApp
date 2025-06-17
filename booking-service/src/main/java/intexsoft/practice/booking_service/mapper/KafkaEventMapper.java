package intexsoft.practice.booking_service.mapper;

import intexsoft.practice.booking_service.dto.KafkaBookingEventDTO;
import intexsoft.practice.booking_service.model.RoomBooking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface KafkaEventMapper {

    @Mapping(source = "roomBooking.bookingId", target = "bookingId")
    @Mapping(source = "roomBooking.userId", target = "userId")
    @Mapping(source = "roomBooking.roomId", target = "roomId")
    @Mapping(source = "roomBooking.checkInDate", target = "checkInDate")
    @Mapping(source = "roomBooking.checkOutDate", target = "checkOutDate")
    KafkaBookingEventDTO toKafkaEventDTO(RoomBooking roomBooking);

    @Mapping(target = "eventType", constant = "BOOKING_CREATED")
    KafkaBookingEventDTO toKafkaEventDTOWithCreatedType(RoomBooking roomBooking);

    @Mapping(target = "eventType", constant = "BOOKING_CANCELLED")
    KafkaBookingEventDTO toKafkaEventDTOWithCancelledType(RoomBooking roomBooking);
}
