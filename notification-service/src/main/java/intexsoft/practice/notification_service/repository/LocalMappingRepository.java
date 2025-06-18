package intexsoft.practice.notification_service.repository;

import intexsoft.practice.notification_service.entity.LocaleMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalMappingRepository extends JpaRepository<LocaleMapping, Long> {
    Optional<LocaleMapping> findByCountryCode(String countryCode);
}
