package intexsoft.practice.notification_service.repository;

import intexsoft.practice.notification_service.entity.BookingNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookingNotificationRepository extends JpaRepository<BookingNotification, UUID> {
}
