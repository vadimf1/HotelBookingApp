package intexsoft.practice.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class AddRoomDto {
    @NotNull
    private UUID hotelId;

    @NotNull
    private UUID roomTypeId;

    @NotNull
    private UUID roomStatusId;

    @NotNull
    @Positive
    private Integer roomNumber;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 10, fraction = 2)
    private Double pricePerDay;

    @NotNull
    @Min(0)
    private Integer floor;

    @Size(max = 2500)
    private String description;

    private List<UUID> amenityIds;
}

