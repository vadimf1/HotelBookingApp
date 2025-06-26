package intexsoft.practice.notification_service.listener;

import intexsoft.practice.dto.notification.AccountLoginNotification;
import intexsoft.practice.notification_service.service.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoginEventListener {

    private final NotificationService<AccountLoginNotification> notificationService;

    @KafkaListener(
            topics = "${spring.kafka.login.topic}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "accountLoginKafkaListenerContainerFactory")
    public void onUserLogin(AccountLoginNotification event) {
        log.info("Получено событие логина: {}", event);
        notificationService.notify(event);
    }
}
