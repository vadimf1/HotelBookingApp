package intexsoft.practice.booking_service.repository;

import intexsoft.practice.booking_service.model.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookingRepository extends JpaRepository<RoomBooking, UUID> {
}
