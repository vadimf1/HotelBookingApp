package intexsoft.practice.repository;

import intexsoft.practice.model.AmenityStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AmenityStatusRepository extends JpaRepository<AmenityStatus, UUID> {
}
