package intexsoft.practice.notification_service.event;

import java.time.Instant;

public record UserLoggedInEvent(
        Long userId,
        Instant timestamp,
        String ip,
        String userAgent,
        String country
) {
}