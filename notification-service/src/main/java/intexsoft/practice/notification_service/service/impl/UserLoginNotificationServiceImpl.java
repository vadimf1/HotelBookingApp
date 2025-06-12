package intexsoft.practice.notification_service.service.impl;

import intexsoft.practice.notification_service.dto.UserLoginNotificationDto;
import intexsoft.practice.notification_service.service.MailSenderService;
import intexsoft.practice.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserLoginNotificationServiceImpl implements NotificationService<UserLoginNotificationDto> {

    private static Map<Long, String> USER_EMAILS = Map.of(
            1L, "user1@example.com",
            2L, "user2@example.com"
    );

    private static final String LOGIN_TEMPLATE = "login-notification.ftl";
    private final MailSenderService mailSenderService;
    private final FreeMarkerMailContentBuilder contentBuilder;

    @Override
    public void notify(UserLoginNotificationDto dto) {
        String email = USER_EMAILS.getOrDefault(dto.userId(), "default@example.com");

        String subject = "Новый вход в аккаунт";
        Map<String, Object> model = Map.of(
                "ip", dto.ip(),
                "country", dto.country(),
                "userAgent", dto.userAgent(),
                "timestamp", dto.timestamp()
        );
        String body = contentBuilder.build(LOGIN_TEMPLATE, model);

        mailSenderService.sendEmail(email, subject, body);
        log.info("Уведомление отправлено на почту {}", email);
    }
}
