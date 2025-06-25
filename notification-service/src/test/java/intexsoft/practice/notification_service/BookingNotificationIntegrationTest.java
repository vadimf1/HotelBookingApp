package intexsoft.practice.notification_service;

import intexsoft.practice.dto.notification.BookingCreatedNotification;
import intexsoft.practice.notification_service.entity.BookingNotification;
import intexsoft.practice.notification_service.repository.BookingNotificationRepository;
import intexsoft.practice.notification_service.service.booking.BookingNotificationServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@Import({WireMockMultiEndpointConfig.class})
public class BookingNotificationIntegrationTest extends AbstractPostgresIntegrationTest {

    @Autowired
    private BookingNotificationServiceImpl bookingNotificationService;

    @Autowired
    private BookingNotificationRepository bookingNotificationRepository;

    @AfterEach
    void tearDown() {
        bookingNotificationRepository.deleteAll();
    }

    @Test
    void testNotify_shouldSaveNotificationAndSendEmail() {
        UUID bookingId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        UUID roomId = UUID.randomUUID();

        BookingCreatedNotification notificationDto = new BookingCreatedNotification(
                bookingId,
                userId,
                roomId,
                LocalDate.now(),
                LocalDate.now().plusDays(3)
        );

        bookingNotificationService.notify(notificationDto);

        List<BookingNotification> saved = bookingNotificationRepository.findAll();
        assertEquals(1, saved.size(), "Должно быть сохранено одно уведомление");

        BookingNotification savedNotification = saved.get(0);
        assertNotNull(savedNotification, "Уведомление не должно быть null");
        assertEquals(bookingId, savedNotification.getId(), "Неверный bookingId");
        assertEquals(userId, savedNotification.getUserId(), "Неверный userId");
        assertEquals(roomId, savedNotification.getRoomId(), "Неверный roomId");
    }
}