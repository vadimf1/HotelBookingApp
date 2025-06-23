package intexsoft.practice.notification_service.service.booking;

import intexsoft.practice.notification_service.entity.BookingNotification;
import intexsoft.practice.notification_service.repository.BookingNotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookingNotificationDataServiceImpl implements BookingNotificationDataService {

    private final BookingNotificationRepository bookingNotificationRepository;

    @Override
    @Transactional
    public void saveBookingNotification(BookingNotification bookingNotification) {
        bookingNotificationRepository.save(bookingNotification);
    }
}
