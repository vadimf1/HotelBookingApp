package intexsoft.practice.repository;

import intexsoft.practice.model.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AmenityRepository extends JpaRepository<Amenity, UUID> {
}
