package intexsoft.practice.notification_service.service.notification;

public interface NotificationService<T> {
    void notify(T dto);
}
