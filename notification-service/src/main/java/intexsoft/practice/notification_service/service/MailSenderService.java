package intexsoft.practice.notification_service.service;

public interface MailSenderService {
    void sendEmail(String to, String subject, String body);
}
