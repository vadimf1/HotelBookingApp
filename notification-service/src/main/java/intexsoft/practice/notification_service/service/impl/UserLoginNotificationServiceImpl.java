package intexsoft.practice.notification_service.service.impl;

import intexsoft.practice.dto.notification.AccountLoginNotification;
import intexsoft.practice.notification_service.dto.IpInfoResponse;
import intexsoft.practice.notification_service.entity.LoginNotification;
import intexsoft.practice.notification_service.mapper.LoginNotificationMapper;
import intexsoft.practice.notification_service.service.IpInfoService;
import intexsoft.practice.notification_service.service.LoginNotificationService;
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
    private final LoginNotificationService loginNotificationService;
    private final LoginNotificationMapper loginNotificationMapper;

    @Override
    public void notify(AccountLoginNotification dto) {
        String email = USER_EMAILS.getOrDefault(dto.userId(), "default@example.com");

        IpInfoResponse ipInfoResponse = ipInfoService.getIpInfo(dto.ip());

        String subject = "Новый вход в аккаунт";
        Map<String, Object> model = Map.of(
                "ip", dto.ip(),
                "userAgent", dto.userAgent(),
                "timestamp", dto.loggedAt(),
                "country", ipInfoResponse.getCountry(),
                "city", ipInfoResponse.getCity()
        );
        String body = contentBuilder.build(LOGIN_TEMPLATE, model);

        LoginNotification loginNotification = loginNotificationMapper.toEntity(dto);
        loginNotification.setCountry(ipInfoResponse.getCountry());
        loginNotification.setCity(ipInfoResponse.getCity());
        loginNotificationService.saveLoginNotification(loginNotification);

        mailSenderService.sendEmail(email, subject, body);
        log.info("Уведомление отправлено на почту {}", email);
    }
}
