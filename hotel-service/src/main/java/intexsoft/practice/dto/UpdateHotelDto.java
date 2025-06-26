package intexsoft.practice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class UpdateHotelDto {
    private UUID id;
    private UUID addressId;
    private UUID hotelStatusId;
    private String name;
    private Integer starRating;
    private Double averageRating;
    private String email;
    private String phoneNumber;
    private String website;
    private List<UUID> amenityIds;
}
