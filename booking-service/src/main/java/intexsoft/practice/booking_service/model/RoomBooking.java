package intexsoft.practice.booking_service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "bookings")
@Data
public class RoomBooking {

    @Id
    @Column(name = "booking_id")
    private UUID bookingId;

    @PrePersist
    public void generateId() {
        if (this.bookingId == null) {
            this.bookingId = UUID.randomUUID();
        }
    }

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "check_in_date", nullable = false)
    private LocalDate checkInDate;

    @Column(name = "check_out_date", nullable = false)
    private LocalDate checkOutDate;

    @Column(name = "room_number", nullable = false)
    private int roomNumber;

    @Column(name = "room_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private BookingStatus roomStatus;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
