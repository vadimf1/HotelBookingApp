package intexsoft.practice.notification_service;

import intexsoft.practice.dto.notification.BookingCreatedNotification;
import intexsoft.practice.notification_service.entity.BookingNotification;
import intexsoft.practice.notification_service.localization.BookingNotificationMessageKeys;
import intexsoft.practice.notification_service.repository.BookingNotificationRepository;
import intexsoft.practice.notification_service.service.booking.BookingNotificationServiceImpl;
import intexsoft.practice.notification_service.service.localization.LocaleMappingService;
import intexsoft.practice.notification_service.service.localization.LocalizedMessageService;
import intexsoft.practice.notification_service.service.mail.FreeMarkerMailContentBuilder;
import intexsoft.practice.notification_service.service.mail.MailSenderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@Import({WireMockMultiEndpointConfig.class, TestMocksConfig.class})
public class BookingNotificationIntegrationTest extends AbstractPostgresIntegrationTest {

    private static final String BOOKING_TEMPLATE = "booking-notification.ftl";

    @Autowired
    private BookingNotificationServiceImpl bookingNotificationService;

    @Autowired
    private BookingNotificationRepository bookingNotificationRepository;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private LocaleMappingService localeMappingService;

    @Autowired
    private LocalizedMessageService localizedMessageService;

    @Autowired
    private FreeMarkerMailContentBuilder contentBuilder;

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

        Map<String, String> messages = new HashMap<>();
        messages.put(BookingNotificationMessageKeys.SUBJECT, "Booking Confirmation");
        String emailBody = "<html>Booking Confirmation Email</html>";

        BookingNotification bookingNotification = new BookingNotification();
        bookingNotification.setId(bookingId);
        bookingNotification.setUserId(userId);
        bookingNotification.setRoomId(roomId);
        bookingNotification.setCheckInDate(LocalDate.now());
        bookingNotification.setCheckOutDate(LocalDate.now().plusDays(3));

        when(localeMappingService.getLocaleForCountry("US")).thenReturn(Locale.US);
        when(localizedMessageService.getBulk(BookingNotificationMessageKeys.ALL_KEYS, Locale.US))
                .thenReturn(messages);
        when(contentBuilder.build(eq(BOOKING_TEMPLATE), any(Map.class))).thenReturn(emailBody);

        bookingNotificationService.notify(notificationDto);

        List<BookingNotification> saved = bookingNotificationRepository.findAll();
        assertEquals(1, saved.size(), "Должно быть сохранено одно уведомление");

        BookingNotification savedNotification = saved.get(0);
        assertNotNull(savedNotification, "Уведомление не должно быть null");
        assertEquals(bookingId, savedNotification.getId(), "Неверный bookingId");
        assertEquals(userId, savedNotification.getUserId(), "Неверный userId");
        assertEquals(roomId, savedNotification.getRoomId(), "Неверный roomId");

        verify(mailSenderService, times(1)).sendEmail(
                eq("mocked.user@example.com"),
                eq("Booking Confirmation"),
                eq(emailBody)
        );

        verify(localeMappingService, times(1)).getLocaleForCountry("US");

        verify(localizedMessageService, times(1))
                .getBulk(BookingNotificationMessageKeys.ALL_KEYS, Locale.US);

        verify(contentBuilder).build(eq(BOOKING_TEMPLATE), argThat(
                model -> model.containsKey("bookingId")
                        && model.containsKey("checkInDate")
                        && model.containsKey("checkOutDate")
                        && model.containsKey("roomNumber")
                        && model.containsKey("roomTypeName")
                        && model.containsKey("roomDescription")
                        && model.containsKey("pricePerDay")
                        && model.containsKey("hotelName")
                        && model.containsKey("hotelEmail")
                        && model.containsKey("hotelPhone")
                        && model.containsKey("hotelWebsite")
                        && model.containsKey("hotelAddress")
                        && model.containsKey("msg")));
    }
}