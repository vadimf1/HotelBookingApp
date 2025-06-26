package intexsoft.practice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AddReviewDto {
    @NotNull
    private UUID id;

    @NotNull
    private UUID userId;

    @NotNull
    private UUID hotelId;

    private UUID roomId;

    @NotNull
    @Min(0)
    @Max(5)
    private Integer rating;

    @Size(max = 2500)
    private String description;
}
