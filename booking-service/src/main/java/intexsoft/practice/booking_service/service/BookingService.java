package intexsoft.practice.booking_service.service;

import intexsoft.practice.booking_service.dto.BookingRequestDTO;
import intexsoft.practice.booking_service.dto.BookingResponseDTO;
import intexsoft.practice.booking_service.dto.RoomAvailabilityDTO;

import java.util.UUID;

public interface BookingService {
    BookingResponseDTO createBooking(BookingRequestDTO bookingRequestDTO);
    BookingResponseDTO cancelBooking(UUID bookingId);
    RoomAvailabilityDTO getBookedPeriods(UUID roomId);
}
