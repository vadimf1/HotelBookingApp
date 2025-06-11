package intexsoft.practice.notification_service.listener;

import intexsoft.practice.notification_service.event.UserLoggedInEvent;
import intexsoft.practice.notification_service.service.NotificationService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoginEventListener {

    private final NotificationService notificationService;

    @PostConstruct
    public void init() {
        log.info("LoginEventListener initialized");
    }

    @KafkaListener(
            topics = "auth.events.login",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void onUserLogin(UserLoggedInEvent event) {
        log.info("Получено событие логина: {}", event);
        notificationService.handleLogin(event);
    }
}
