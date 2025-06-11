package intexsoft.practice.notification_service.service.impl;

import intexsoft.practice.notification_service.event.UserLoggedInEvent;
import intexsoft.practice.notification_service.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void handleLogin(UserLoggedInEvent event) {
        // TODO: здесь будет логика отправки письма и сохранения в БД
        log.info("→ NotificationService.handleLogin:" +
                " будем отправлять уведомление по логину для userId={}", event.getUserId());
    }
}
