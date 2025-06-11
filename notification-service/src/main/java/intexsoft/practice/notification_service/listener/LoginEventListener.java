package intexsoft.practice.notification_service.listener;

import intexsoft.practice.notification_service.dto.UserLoginNotificationDto;
import intexsoft.practice.notification_service.event.UserLoggedInEvent;
import intexsoft.practice.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoginEventListener {

    private final NotificationService<UserLoginNotificationDto> notificationService;

    @KafkaListener(topics = "auth.events.login", groupId = "${spring.kafka.consumer.group-id}")
    public void onUserLogin(UserLoggedInEvent event) {
        UserLoginNotificationDto userLoginNotificationDto = UserLoginNotificationDto.from(event);

        try {
            log.info("Получено событие логина: {}", event);
            notificationService.notify(userLoginNotificationDto);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }
}
