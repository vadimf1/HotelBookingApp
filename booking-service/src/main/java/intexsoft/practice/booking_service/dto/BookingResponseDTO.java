package intexsoft.practice.booking_service.dto;

import intexsoft.practice.booking_service.model.BookingStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BookingResponseDTO {

    private UUID bookingId;
    private UUID userId;
    private UUID roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    private BookingStatus bookingStatus;

    private LocalDateTime createdAt;
}
