package intexsoft.practice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Builder;

import java.util.UUID;

@Data
@Builder
public class RoomTypeDto {
    @NotNull
    private UUID id;

    @NotBlank
    @Size(max = 50)
    private String code;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotNull
    private Integer capacity;

    @NotNull
    private Integer bedCount;

    @NotNull
    private Integer area;
}
