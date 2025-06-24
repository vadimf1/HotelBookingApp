package intexsoft.practice.notification_service;

import intexsoft.practice.dto.notification.AccountLoginNotification;
import intexsoft.practice.notification_service.dto.IpInfoResponse;
import intexsoft.practice.notification_service.entity.LoginNotification;
import intexsoft.practice.notification_service.localization.NotificationLoginMessageKeys;
import intexsoft.practice.notification_service.mapper.LoginNotificationMapper;
import intexsoft.practice.notification_service.repository.LoginNotificationRepository;
import intexsoft.practice.notification_service.service.client.UserClientService;
import intexsoft.practice.notification_service.service.ip.IpInfoService;
import intexsoft.practice.notification_service.service.localization.LocaleMappingService;
import intexsoft.practice.notification_service.service.localization.LocalizedMessageService;
import intexsoft.practice.notification_service.service.mail.FreeMarkerMailContentBuilder;
import intexsoft.practice.notification_service.service.mail.MailSenderService;
import intexsoft.practice.notification_service.service.user.UserLoginNotificationServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@Import({WireMockMultiEndpointConfig.class, TestMocksConfig.class})
public class UserLoginNotificationIntegrationTest extends AbstractPostgresIntegrationTest {

    @Autowired
    private UserLoginNotificationServiceImpl userLoginNotificationServiceImpl;

    @Autowired
    private LoginNotificationRepository loginNotificationRepository;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private LocaleMappingService localeMappingService;

    @Autowired
    private LocalizedMessageService localizedMessageService;

    @Autowired
    private FreeMarkerMailContentBuilder contentBuilder;

    @Autowired
    private IpInfoService ipInfoService;

    @AfterEach
    void tearDown() {
        loginNotificationRepository.deleteAll();
    }

    @Test
    void testNotify_shouldSaveNotificationAndSendEmail() {
        UUID userId = UUID.randomUUID();
        String ip = "8.8.8.8";
        String agent = "Google Chrome";
        Instant loggedAt = Instant.parse("2024-06-24T10:00:00Z");

        AccountLoginNotification accountLoginNotification = new AccountLoginNotification(
                userId,
                ip,
                agent,
                loggedAt
        );

        IpInfoResponse ipInfoResponse = new IpInfoResponse();
        ipInfoResponse.setCountry("United States");
        ipInfoResponse.setCity("Ashburn");
        ipInfoResponse.setCountryCode("US");

        Map<String, String> messages = new HashMap<>();
        messages.put(NotificationLoginMessageKeys.SUBJECT, "Login Notification");
        String emailBody = "<html>Login Notification</html>";

        LoginNotification loginNotification = new LoginNotification(
                null,
                ip,
                ipInfoResponse.getCountry(),
                ipInfoResponse.getCity(),
                agent,
                userId,
                loggedAt,
                null
        );

        when(ipInfoService.getIpInfo(ip)).thenReturn(ipInfoResponse);
        when(localeMappingService.getLocaleForCountry(ipInfoResponse.getCountryCode())).thenReturn(Locale.US);
        when(localizedMessageService.getBulk(NotificationLoginMessageKeys.ALL_KEYS, Locale.US))
                .thenReturn(messages);
        when(contentBuilder.build(eq("login-notification.ftl"), any(Map.class))).thenReturn(emailBody);

        userLoginNotificationServiceImpl.notify(accountLoginNotification);

        List<LoginNotification> saved = loginNotificationRepository.findAll();
        assertEquals(1, saved.size());

        LoginNotification savedLoginNotification = saved.get(0);
        assertNotNull(savedLoginNotification);
        assertEquals(userId, savedLoginNotification.getUserId());
        assertEquals(ip, savedLoginNotification.getIp());
        assertEquals(agent, savedLoginNotification.getUserAgent());
        assertEquals(loggedAt, savedLoginNotification.getLoggedAt());

        verify(mailSenderService, times(1)).sendEmail(
                eq("mocked.user@example.com"),
                eq("Login Notification"),
                eq(emailBody)
        );

        verify(localeMappingService, times(1)).getLocaleForCountry("US");
        verify(localizedMessageService, times(1))
                .getBulk(NotificationLoginMessageKeys.ALL_KEYS, Locale.US);
        verify(contentBuilder).build(eq("login-notification.ftl"), argThat(
                model -> model.containsKey("ip")
                        && model.containsKey("country")
                        && model.containsKey("city")
                        && model.containsKey("userAgent")
                        && model.containsKey("timestamp")));
    }
}


