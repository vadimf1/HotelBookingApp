package intexsoft.practice.notification_service.dto;

import intexsoft.practice.notification_service.event.UserLoggedInEvent;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record UserLoginNotificationDto(
        @NotNull Long userId,
        @NotBlank @Email String email,
        @NotBlank String ip,
        @NotBlank String userAgent,
        @NotNull Instant timestamp,
        String country
) {
    public static UserLoginNotificationDto from(UserLoggedInEvent event) {
        return new UserLoginNotificationDto(
                event.userId(),
                event.email(),
                event.ip(),
                event.userAgent(),
                event.timestamp(),
                event.country()
        );
    }
}
