package intexsoft.practice.repository;

import intexsoft.practice.model.HotelStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HotelStatusRepository extends JpaRepository<HotelStatus, UUID> {
}
