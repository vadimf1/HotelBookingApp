package intexsoft.practice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UpdateReviewDto {
    private UUID id;
    private UUID userId;
    private UUID hotelId;
    private UUID roomId;
    private Integer rating;
    private String description;
}
