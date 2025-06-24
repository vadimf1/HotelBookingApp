package intexsoft.practice.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class AddHotelDto {
    @NotNull
    private UUID id;

    @NotNull
    private UUID addressId;

    @NotNull
    private UUID hotelStatusId;

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer starRating;

    @DecimalMin(value = "0.0", inclusive = true)
    @DecimalMax(value = "5.0", inclusive = true)
    @Digits(integer = 1, fraction = 2)
    private Double averageRating;

    @Email
    @Size(max = 100)
    private String email;

    @Size(max = 20)
    private String phoneNumber;

    @Size(max = 100)
    private String website;

    private List<UUID> amenityIds;
}
