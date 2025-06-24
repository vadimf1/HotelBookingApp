package intexsoft.practice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ResponseReviewDto {
    private UUID id;
    private ClientDto client;
    private HotelDto hotel;
    private RoomDto room;
    private Integer rating;
    private String description;
    private LocalDateTime createdAt;
}
