package intexsoft.practice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AddAmenityDto {
    @NotNull
    private UUID amenityStatusId;

    @NotBlank
    @Size(max = 100)
    private String name;

    @Size(max = 2500)
    private String description;

    @NotNull
    private Boolean isFree;
}
