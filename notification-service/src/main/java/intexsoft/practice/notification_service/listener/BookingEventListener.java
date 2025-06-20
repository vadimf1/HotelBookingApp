package intexsoft.practice.notification_service.listener;

import intexsoft.practice.dto.notification.BookingCreatedNotification;
import intexsoft.practice.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookingEventListener {

    private final NotificationService<BookingCreatedNotification> notificationService;

    @KafkaListener(
            topics = "${spring.kafka.booking.topic}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "bookingKafkaListenerContainerFactory")
    public void onBookingCreated(BookingCreatedNotification event) {
        log.info("Получено событие бронирования: {}", event);
        notificationService.notify(event);
    }
}
