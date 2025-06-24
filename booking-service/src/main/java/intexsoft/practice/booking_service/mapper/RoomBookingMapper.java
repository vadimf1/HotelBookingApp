package intexsoft.practice.booking_service.mapper;

import intexsoft.practice.booking_service.dto.BookingRequestDTO;
import intexsoft.practice.booking_service.dto.BookingResponseDTO;
import intexsoft.practice.booking_service.model.BookingStatusEntity;
import intexsoft.practice.booking_service.model.Room;
import intexsoft.practice.booking_service.model.RoomBooking;
import org.mapstruct.*;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface RoomBookingMapper {

    @Mapping(target = "room", ignore = true)
    @Mapping(target = "bookingStatus", ignore = true)
    RoomBooking partialToEntity(BookingRequestDTO requestDTO);

    default RoomBooking toEntity(BookingRequestDTO requestDTO,
                                 Room room,
                                 UUID userId,
                                 BookingStatusEntity bookingStatus) {
        RoomBooking roomBooking = partialToEntity(requestDTO);
        roomBooking.setRoom(room);
        roomBooking.setUserId(userId);
        roomBooking.setBookingStatus(bookingStatus);
        return roomBooking;
    }

    @Mapping(source = "roomBooking.bookingStatus.code", target = "bookingStatus")
    BookingResponseDTO toDTO(RoomBooking roomBooking);
}
