package intexsoft.practice.notification_service.service.impl;

import intexsoft.practice.notification_service.dto.UserLoginNotificationDto;
import intexsoft.practice.notification_service.service.MailSenderService;
import intexsoft.practice.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserLoginNotificationServiceImpl implements NotificationService<UserLoginNotificationDto> {

    private final MailSenderService mailSenderService;

    @Override
    public void notify(UserLoginNotificationDto dto) {
        String email = dto.email();

        String subject = "Новая авторизация в системе";
        String body = String.format("""
                        Привет!
                        Кто-то вошёл в ваш аккаунт.

                        IP: %s
                        Страна: %s
                        Браузер: %s
                        Время: %s
                        """,
                dto.ip(),
                dto.country(),
                dto.userAgent(),
                dto.timestamp()
        );

        mailSenderService.sendEmail(email, subject, body);
        log.info("Уведомление отправлено на почту {}", email);
    }
}
