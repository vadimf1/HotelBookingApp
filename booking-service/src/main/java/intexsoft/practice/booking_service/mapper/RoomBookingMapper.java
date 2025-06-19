package intexsoft.practice.booking_service.mapper;

import intexsoft.practice.booking_service.dto.BookingRequestDTO;
import intexsoft.practice.booking_service.dto.BookingResponseDTO;
import intexsoft.practice.booking_service.model.BookingStatusEntity;
import intexsoft.practice.booking_service.model.RoomBooking;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RoomBookingMapper {

    RoomBooking partialToEntity(BookingRequestDTO requestDTO);

    default RoomBooking toEntity(BookingRequestDTO requestDTO, BookingStatusEntity bookingStatus) {
        RoomBooking roomBooking = partialToEntity(requestDTO);
        roomBooking.setBookingStatus(bookingStatus);
        return roomBooking;
    }

    @Mapping(source = "roomBooking.bookingStatus.code", target = "bookingStatus")
    BookingResponseDTO toDTO(RoomBooking roomBooking);
}
