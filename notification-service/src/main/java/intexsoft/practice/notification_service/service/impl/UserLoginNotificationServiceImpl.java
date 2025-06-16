package intexsoft.practice.notification_service.service.impl;

import intexsoft.practice.dto.notification.AccountLoginNotification;
import intexsoft.practice.notification_service.service.IpInfoService;
import intexsoft.practice.notification_service.service.MailSenderService;
import intexsoft.practice.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserLoginNotificationServiceImpl implements NotificationService<AccountLoginNotification> {

    private static Map<Long, String> USER_EMAILS = Map.of(
            1L, "user1@example.com",
            2L, "user2@example.com"
    );

    private static final String LOGIN_TEMPLATE = "login-notification.ftl";
    private final MailSenderService mailSenderService;
    private final FreeMarkerMailContentBuilder contentBuilder;
    private final IpInfoService ipInfoService;

    @Override
    public void notify(AccountLoginNotification dto) {
        String email = USER_EMAILS.getOrDefault(dto.userId(), "default@example.com");

        String location = ipInfoService.getCountryAndCity(dto.ip());

        String subject = "Новый вход в аккаунт";
        Map<String, Object> model = Map.of(
                "ip", dto.ip(),
                "userAgent", dto.userAgent(),
                "timestamp", dto.timestamp(),
                "country", location
        );
        String body = contentBuilder.build(LOGIN_TEMPLATE, model);

        mailSenderService.sendEmail(email, subject, body);
        log.info("Уведомление отправлено на почту {}", email);
    }
}
