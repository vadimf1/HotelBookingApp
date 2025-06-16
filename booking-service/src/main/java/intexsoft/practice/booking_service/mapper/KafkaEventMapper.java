package intexsoft.practice.booking_service.mapper;

import intexsoft.practice.booking_service.dto.KafkaBookingEventDTO;
import intexsoft.practice.booking_service.dto.KafkaEventType;
import intexsoft.practice.booking_service.model.RoomBooking;

import java.time.LocalDateTime;

public class KafkaEventMapper {

    private static KafkaBookingEventDTO toKafkaEvent(RoomBooking roomBooking) {
        KafkaBookingEventDTO eventDTO = new KafkaBookingEventDTO();
        eventDTO.setBookingId(roomBooking.getBookingId());
        eventDTO.setRoomId(roomBooking.getRoomId());
        eventDTO.setUserId(roomBooking.getUserId());
        eventDTO.setCheckInDate(roomBooking.getCheckInDate());
        eventDTO.setCheckOutDate(roomBooking.getCheckOutDate());
        eventDTO.setEventTime(LocalDateTime.now());

        return eventDTO;
    }

    public static KafkaBookingEventDTO toKafkaEventWithCreatedType(RoomBooking roomBooking) {
        KafkaBookingEventDTO eventDTO = toKafkaEvent(roomBooking);
        eventDTO.setEventType(KafkaEventType.BOOKING_CREATED);

        return eventDTO;
    }

    public static KafkaBookingEventDTO toKafkaEventWithCancelledType(RoomBooking roomBooking) {
        KafkaBookingEventDTO eventDTO = toKafkaEvent(roomBooking);
        eventDTO.setEventType(KafkaEventType.BOOKING_CANCELLED);

        return eventDTO;
    }
}
