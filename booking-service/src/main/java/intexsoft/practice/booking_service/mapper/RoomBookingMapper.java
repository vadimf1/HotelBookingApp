package intexsoft.practice.booking_service.mapper;

import intexsoft.practice.booking_service.dto.BookingRequestDTO;
import intexsoft.practice.booking_service.dto.BookingResponseDTO;
import intexsoft.practice.booking_service.model.BookingStatusEntity;
import intexsoft.practice.booking_service.model.RoomBooking;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RoomBookingMapper {

    @Mapping(source = "requestDTO.userId", target = "userId")
    @Mapping(source = "requestDTO.roomId", target = "roomId")
    @Mapping(source = "requestDTO.checkInDate", target = "checkInDate")
    @Mapping(source = "requestDTO.checkOutDate", target = "checkOutDate")
    RoomBooking partialToEntity(BookingRequestDTO requestDTO);

    default RoomBooking toEntity(BookingRequestDTO requestDTO, BookingStatusEntity bookingStatus) {
        RoomBooking roomBooking = partialToEntity(requestDTO);
        roomBooking.setBookingStatus(bookingStatus);
        return roomBooking;
    }

    @Mapping(source = "roomBooking.bookingId", target = "bookingId")
    @Mapping(source = "roomBooking.userId", target = "userId")
    @Mapping(source = "roomBooking.roomId", target = "roomId")
    @Mapping(source = "roomBooking.checkInDate", target = "checkInDate")
    @Mapping(source = "roomBooking.checkOutDate", target = "checkOutDate")
    @Mapping(source = "roomBooking.bookingStatus", target = "bookingStatus")
    @Mapping(source = "roomBooking.createdAt", target = "createdAt")
    BookingResponseDTO toDTO(RoomBooking roomBooking);
}
