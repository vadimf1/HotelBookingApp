package intexsoft.practice.notification_service.repository;

import intexsoft.practice.notification_service.entity.BookingNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookingNotificationRepository extends JpaRepository<BookingNotification, UUID> {
}
