package intexsoft.practice.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class KafkaResponseRoomDto {
    private UUID id;
    private UUID hotelId;
    private UUID roomTypeId;
    private UUID roomStatusId;
    private Integer roomNumber;
    private Double pricePerDay;
    private Integer floor;
    private String description;
    private List<UUID> amenityIds;
}
