package intexsoft.practice.notification_service.repository;

import intexsoft.practice.notification_service.entity.LoginNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LoginNotificationRepository extends JpaRepository<LoginNotification, UUID> {
}
