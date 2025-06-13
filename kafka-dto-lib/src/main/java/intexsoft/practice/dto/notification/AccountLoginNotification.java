package intexsoft.practice.dto.notification;

import intexsoft.practice.dto.AppEvent;

import java.time.Instant;

public record AccountLoginNotification(
        Long userId,
        String ip,
        String userAgent,
        Instant timestamp) implements AppEvent {
}
