package intexsoft.practice.booking_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookedPeriodDTO {

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}
