package intexsoft.practice.notification_service.service;

public interface NotificationService<T> {
    void notify(T dto);
}
