package intexsoft.practice.notification_service.event;

import lombok.Builder;
import lombok.Value;

import java.time.Instant;
import java.time.LocalDate;

@Value
@Builder
public class RoomBookedEvent {
    Long bookingId;
    Long userId;
    Long roomId;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    Instant timestamp;
}
