package intexsoft.practice.notification_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "locale_mappings")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LocaleMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "country_code", nullable = false, unique = true)
    private String countryCode;

    @Column(name = "locale_code", nullable = false)
    private String localeCode;
}
