package intexsoft.practice.repository;

import intexsoft.practice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<User, UUID> {
}
