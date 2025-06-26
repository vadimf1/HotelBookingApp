package intexsoft.practice.dto.notification;

import intexsoft.practice.dto.AppEvent;

import java.time.LocalDate;
import java.util.UUID;

public record BookingCreatedNotification(
        UUID bookingId,
        UUID userId,
        UUID roomId,
        LocalDate checkInDate,
        LocalDate checkOutDate
) implements AppEvent {
}
