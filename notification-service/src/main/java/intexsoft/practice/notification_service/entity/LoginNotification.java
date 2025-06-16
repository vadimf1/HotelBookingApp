package intexsoft.practice.notification_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Table(name = "login_notifications")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ip", nullable = false)
    private String ip;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "user_agent", nullable = false)
    private String userAgent;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "timestamp", nullable = false)
    private Instant loggedAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
    }
}
