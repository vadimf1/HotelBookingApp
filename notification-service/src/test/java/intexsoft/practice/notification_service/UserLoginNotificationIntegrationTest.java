package intexsoft.practice.notification_service;

import intexsoft.practice.dto.notification.AccountLoginNotification;
import intexsoft.practice.notification_service.entity.LoginNotification;
import intexsoft.practice.notification_service.repository.LoginNotificationRepository;
import intexsoft.practice.notification_service.service.user.UserLoginNotificationServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@Import({WireMockMultiEndpointConfig.class})
public class UserLoginNotificationIntegrationTest extends AbstractPostgresIntegrationTest {

    @Autowired
    private UserLoginNotificationServiceImpl userLoginNotificationServiceImpl;

    @Autowired
    private LoginNotificationRepository loginNotificationRepository;

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

        userLoginNotificationServiceImpl.notify(accountLoginNotification);

        List<LoginNotification> saved = loginNotificationRepository.findAll();
        assertEquals(1, saved.size());

        LoginNotification savedLoginNotification = saved.get(0);
        assertNotNull(savedLoginNotification);
        assertEquals(userId, savedLoginNotification.getUserId());
        assertEquals(ip, savedLoginNotification.getIp());
        assertEquals(agent, savedLoginNotification.getUserAgent());
        assertEquals(loggedAt, savedLoginNotification.getLoggedAt());
    }
}


