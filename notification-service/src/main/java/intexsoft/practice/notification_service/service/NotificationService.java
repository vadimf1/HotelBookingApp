package intexsoft.practice.notification_service.service;

import intexsoft.practice.notification_service.event.UserLoggedInEvent;

public interface NotificationService {
    void handleLogin(UserLoggedInEvent event);
}
