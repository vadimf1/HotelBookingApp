package intexsoft.practice.repository;

import intexsoft.practice.model.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoomStatusRepository extends JpaRepository<RoomStatus, UUID> {
}
