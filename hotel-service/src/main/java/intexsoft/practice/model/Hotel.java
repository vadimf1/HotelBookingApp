package intexsoft.practice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @UuidGenerator
    @Column(name = "id")
    private UUID id;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "hotel_status_id")
    private HotelStatus hotelStatus;

    @Column(name = "name")
    private String name;

    @Column(name = "star_rating")
    private Integer starRating;

    @Column(name = "average_rating")
    private Integer averageRating;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "website")
    private String website;

    @ManyToMany
    @JoinTable(
            name = "hotel_amenities",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id")
    )
    private Set<Amenity> amenities;

    public void addAmenity(Amenity amenity) {
        if (amenities == null) {
            amenities = new HashSet<>();
        }
        amenities.add(amenity);
    }
}
