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
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Import(WireMockMultiEndpointConfig.class)
@Testcontainers
@Slf4j
public class UserLoginNotificationIntegrationTest extends AbstractPostgresIntegrationTest {

    private static final String LOGIN_TEMPLATE = "login-notification.ftl";

    @Autowired
    private UserLoginNotificationServiceImpl userLoginNotificationServiceImpl;

    @Autowired
    private LoginNotificationRepository loginNotificationRepository;

    @MockBean
    private MailSenderService mailSenderService;

    @MockBean
    private UserClientService userClientService;

    @MockBean
    private LocaleMappingService localeMappingService;

    @MockBean
    private LocalizedMessageService localizedMessageService;

    @MockBean
    private FreeMarkerMailContentBuilder contentBuilder;

    @MockBean
    private LoginNotificationMapper loginNotificationMapper;

    @MockBean
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

        when(userClientService.getUserById(userId)).thenReturn(StubDataFactory.createUserDto());
        when(ipInfoService.getIpInfo(ip)).thenReturn(ipInfoResponse);

        Map<String, String> messages = new HashMap<>();
        messages.put(NotificationLoginMessageKeys.SUBJECT, "Login Notification");
        String emailBody = "<html>Login Notification</html>";

        LoginNotification loginNotification = new LoginNotification(
                null,
                accountLoginNotification.ip(),
                ipInfoResponse.getCountry(),
                ipInfoResponse.getCity(),
                accountLoginNotification.userAgent(),
                accountLoginNotification.userId(),
                accountLoginNotification.loggedAt(),
                null
        );

        when(localeMappingService.getLocaleForCountry(ipInfoResponse.getCountryCode())).thenReturn(Locale.US);
        when(localizedMessageService.getBulk(NotificationLoginMessageKeys.ALL_KEYS, Locale.US))
                .thenReturn(messages);
        when(contentBuilder.build(eq(LOGIN_TEMPLATE), any(Map.class))).thenReturn(emailBody);
        when(loginNotificationMapper.toEntity(accountLoginNotification)).thenReturn(loginNotification);

        userLoginNotificationServiceImpl.notify(accountLoginNotification);

        List<LoginNotification> saved = loginNotificationRepository.findAll();
        assertEquals(1, saved.size(), "Должно быть сохранено одно уведомление");

        LoginNotification savedLoginNotification = saved.get(0);
        assertNotNull(savedLoginNotification, "Уведомление не должно быть null");
        assertEquals(userId, savedLoginNotification.getUserId(), "Неверный userId");
        assertEquals(ip, savedLoginNotification.getIp(), "Неверный IP");
        assertEquals(agent, savedLoginNotification.getUserAgent(), "Неверный agent");
        assertEquals(loggedAt, savedLoginNotification.getLoggedAt(), "Неверный loggedAt");

        verify(mailSenderService, times(1)).sendEmail(
                eq("mocked.user@example.com"),
                eq("Login Notification"),
                eq(emailBody)
        );

        verify(localeMappingService, times(1)).getLocaleForCountry("US");

        verify(localizedMessageService, times(1))
                .getBulk(NotificationLoginMessageKeys.ALL_KEYS, Locale.US);

        verify(contentBuilder).build(eq(LOGIN_TEMPLATE), argThat(
                model -> model.containsKey("ip")
                        && model.containsKey("country")
                        && model.containsKey("city")
                        && model.containsKey("userAgent")
                        && model.containsKey("timestamp")));
    }
}
