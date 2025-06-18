package intexsoft.practice.notification_service.service.impl;

import intexsoft.practice.dto.notification.AccountLoginNotification;
import intexsoft.practice.notification_service.dto.IpInfoResponse;
import intexsoft.practice.notification_service.entity.LoginNotification;
import intexsoft.practice.notification_service.localization.NotificationLoginMessageKeys;
import intexsoft.practice.notification_service.mapper.LoginNotificationMapper;
import intexsoft.practice.notification_service.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserLoginNotificationServiceImpl implements NotificationService<AccountLoginNotification> {

    private static final String LOGIN_TEMPLATE = "login-notification.ftl";
    private final MailSenderService mailSenderService;
    private final FreeMarkerMailContentBuilder contentBuilder;
    private final IpInfoService ipInfoService;
    private final LoginNotificationService loginNotificationService;
    private final LoginNotificationMapper loginNotificationMapper;
    private final LocalizedMessageService localizedMessageService;
    private final LocaleMappingService localeMappingService;

    @Override
    public void notify(AccountLoginNotification dto) {
        String email = "default@example.com";

        IpInfoResponse ipInfoResponse = ipInfoService.getIpInfo(dto.ip());

        Map<String, Object> model = new HashMap<>();
        model.put("ip", dto.ip());
        model.put("userAgent", dto.userAgent());
        model.put("timestamp", dto.loggedAt());
        model.put("country", ipInfoResponse.getCountry());
        model.put("city", ipInfoResponse.getCity());

        Locale locale = localeMappingService
                .getLocaleForCountry(ipInfoResponse.getCountryCode());
        Map<String, String> msg = localizedMessageService
                .getBulk(NotificationLoginMessageKeys.ALL_KEYS,locale);
        model.put("msg", msg);

        String subject = msg.get(NotificationLoginMessageKeys.SUBJECT);

        String body = contentBuilder.build(LOGIN_TEMPLATE, model);

        LoginNotification loginNotification = loginNotificationMapper.toEntity(dto);
        loginNotification.setCountry(ipInfoResponse.getCountry());
        loginNotification.setCity(ipInfoResponse.getCity());
        loginNotificationService.saveLoginNotification(loginNotification);

        mailSenderService.sendEmail(email, subject, body);
        log.info("Уведомление отправлено на почту {}", email);
    }
}
