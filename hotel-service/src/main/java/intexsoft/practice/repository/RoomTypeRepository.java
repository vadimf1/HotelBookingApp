package intexsoft.practice.repository;

import intexsoft.practice.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoomTypeRepository extends JpaRepository<RoomType, UUID> {
}
