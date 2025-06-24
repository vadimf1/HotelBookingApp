package intexsoft.practice.notification_service;

import intexsoft.practice.dto.notification.BookingCreatedNotification;
import intexsoft.practice.notification_service.entity.BookingNotification;
import intexsoft.practice.notification_service.localization.BookingNotificationMessageKeys;
import intexsoft.practice.notification_service.mapper.BookingNotificationMapper;
import intexsoft.practice.notification_service.repository.BookingNotificationRepository;
import intexsoft.practice.notification_service.service.booking.BookingNotificationServiceImpl;
import intexsoft.practice.notification_service.service.client.RoomClientService;
import intexsoft.practice.notification_service.service.client.UserClientService;
import intexsoft.practice.notification_service.service.localization.LocaleMappingService;
import intexsoft.practice.notification_service.service.localization.LocalizedMessageService;
import intexsoft.practice.notification_service.service.mail.FreeMarkerMailContentBuilder;
import intexsoft.practice.notification_service.service.mail.MailSenderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
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
public class BookingNotificationIntegrationTest extends AbstractPostgresIntegrationTest {

    @Autowired
    private BookingNotificationServiceImpl bookingNotificationService;

    @Autowired
    private BookingNotificationRepository bookingNotificationRepository;

    @MockBean
    private MailSenderService mailSenderService;

    @MockBean
    private UserClientService userClientService;

    @MockBean
    private RoomClientService roomClientService;

    @MockBean
    private LocaleMappingService localeMappingService;

    @MockBean
    private LocalizedMessageService localizedMessageService;

    @MockBean
    private FreeMarkerMailContentBuilder contentBuilder;

    @MockBean
    private BookingNotificationMapper bookingNotificationMapper;

    @AfterEach
    void tearDown() {
        bookingNotificationRepository.deleteAll();
    }

    @Test
    void testNotify_shouldSaveNotificationAndSendEmail() {
        UUID bookingId = UUID.fromString("123e4567-e89b-12d3-a456-426614174013");
        UUID userId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
        UUID roomId = UUID.fromString("987e6543-e21b-32c3-b654-426614174999");

        BookingCreatedNotification notificationDto = new BookingCreatedNotification(
                bookingId,
                userId,
                roomId,
                LocalDate.now(),
                LocalDate.now().plusDays(3)
        );

        when(userClientService.getUserById(userId)).thenReturn(StubDataFactory.createUserDto());
        when(roomClientService.getRoomById(roomId)).thenReturn(StubDataFactory.createRoomDto());

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
        when(contentBuilder.build(eq("booking-notification.ftl"), any(Map.class))).thenReturn(emailBody);
        when(bookingNotificationMapper.toEntity(notificationDto)).thenReturn(bookingNotification);

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

        verify(localizedMessageService, times(1)).getBulk(BookingNotificationMessageKeys.ALL_KEYS, Locale.US);

        verify(contentBuilder).build(eq("booking-notification.ftl"), argThat(
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