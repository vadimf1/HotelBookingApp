package intexsoft.practice.dto.notification;

import intexsoft.practice.dto.AppEvent;

import java.time.Instant;
import java.util.UUID;

public record AccountLoginNotification(
        UUID userId,
        String ip,
        String userAgent,
        Instant loggedAt) implements AppEvent {
}
