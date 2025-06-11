package intexsoft.practice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Column(name = "room_type")
    private RoomType roomType;

    @Column(name = "room_number")
    private Integer roomNumber;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "price_per_day")
    private Double pricePerDay;

    @Column(name = "status")
    private RoomStatus status;

    @Column(name = "area")
    private Integer area;

    @Column(name = "floor")
    private Integer floor;

    @Column(name = "bed_count")
    private Integer bedCount;

    @Column(name = "description")
    private String description;
}
