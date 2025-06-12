package intexsoft.practice.notification_service.service;

public interface MailSenderService {
    void sendEmail(String recipientEmail, String subject, String body);
}
