package intexsoft.practice.notification_service.listener;

import intexsoft.practice.dto.notification.AccountLoginNotification;
import intexsoft.practice.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoginEventListener {

    private final NotificationService<AccountLoginNotification> notificationService;

    @KafkaListener(topics = "auth.events.login", groupId = "${spring.kafka.consumer.group-id}")
    public void onUserLogin(AccountLoginNotification event) {
        log.info("Получено событие логина: {}", event);
        notificationService.notify(event);
    }
}
