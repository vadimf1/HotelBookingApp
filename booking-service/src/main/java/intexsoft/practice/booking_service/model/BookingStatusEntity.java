package intexsoft.practice.booking_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "booking_status")
public class BookingStatusEntity {

    @Id
    @Column(name = "status_id", nullable = false)
    private UUID statusId;

    @Column(name = "code", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private BookingStatus code;

    @Column(name = "name", nullable = false)
    private String name;
}
