package intexsoft.practice.booking_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class KafkaBookingEventDTO {

    private KafkaEventType eventType;
    private UUID bookingId;
    private UUID userId;
    private UUID roomId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkInDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkOutDate;
}
