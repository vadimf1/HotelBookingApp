package intexsoft.practice.booking_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    private UUID id;

    private UUID hotelId;
    private UUID roomTypeId;
    private UUID roomStatusId;
    private Integer roomNumber;
    private Double pricePerDay;
    private Integer floor;
    private String description;

    @Column(columnDefinition = "TEXT")
    private List<UUID> amenityIds;
}
