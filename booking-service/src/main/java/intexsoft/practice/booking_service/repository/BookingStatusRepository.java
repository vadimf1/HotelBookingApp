package intexsoft.practice.booking_service.repository;

import intexsoft.practice.booking_service.model.BookingStatus;
import intexsoft.practice.booking_service.model.BookingStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookingStatusRepository extends JpaRepository<BookingStatusEntity, UUID> {
    Optional<BookingStatusEntity> findByCode(BookingStatus code);
}
