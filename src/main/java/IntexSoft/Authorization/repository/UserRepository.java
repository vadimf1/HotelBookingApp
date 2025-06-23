package IntexSoft.Authorization.repository;

import IntexSoft.Authorization.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmailAndPassword(String firstName, String lastName);

    boolean existsByEmail(String email);


}
