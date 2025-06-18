package intexsoft.practice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class UpdateAmenityDto {
    private UUID amenityStatusId;
    private String name;
    private String description;
    private Boolean isFree;
}
