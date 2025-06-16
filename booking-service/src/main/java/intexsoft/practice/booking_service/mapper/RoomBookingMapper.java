package intexsoft.practice.booking_service.mapper;

import intexsoft.practice.booking_service.dto.BookingRequestDTO;
import intexsoft.practice.booking_service.dto.BookingResponseDTO;
import intexsoft.practice.booking_service.model.BookingStatusEntity;
import intexsoft.practice.booking_service.model.RoomBooking;

import java.time.LocalDateTime;

public class RoomBookingMapper {

    public static RoomBooking toEntity(BookingRequestDTO requestDTO, BookingStatusEntity bookingStatus) {
        RoomBooking roomBooking = new RoomBooking();
        roomBooking.setUserId(requestDTO.getUserId());
        roomBooking.setRoomId(requestDTO.getRoomId());
        roomBooking.setCheckInDate(requestDTO.getCheckInDate());
        roomBooking.setCheckOutDate(requestDTO.getCheckOutDate());
        roomBooking.setBookingStatus(bookingStatus);
        roomBooking.setCreatedAt(LocalDateTime.now());

        return roomBooking;
    }

    public static BookingResponseDTO toDTO(RoomBooking roomBooking) {
        BookingResponseDTO responseDTO = new BookingResponseDTO();
        responseDTO.setBookingId(roomBooking.getBookingId());
        responseDTO.setUserId(roomBooking.getUserId());
        responseDTO.setRoomId(roomBooking.getRoomId());
        responseDTO.setCheckInDate(roomBooking.getCheckInDate());
        responseDTO.setCheckOutDate(roomBooking.getCheckOutDate());
        responseDTO.setBookingStatus(roomBooking.getBookingStatus());
        responseDTO.setCreatedAt(roomBooking.getCreatedAt());

        return responseDTO;
    }
}
