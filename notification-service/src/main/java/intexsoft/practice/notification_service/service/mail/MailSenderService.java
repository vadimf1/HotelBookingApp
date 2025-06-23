package intexsoft.practice.notification_service.service.mail;

public interface MailSenderService {
    void sendEmail(String recipientEmail, String subject, String body);
}
