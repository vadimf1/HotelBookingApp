package intexsoft.practice.booking_service.dto;

import intexsoft.practice.booking_service.model.BookingStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class BookingResponseDTO {

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private BookingStatus bookingStatus;
}
