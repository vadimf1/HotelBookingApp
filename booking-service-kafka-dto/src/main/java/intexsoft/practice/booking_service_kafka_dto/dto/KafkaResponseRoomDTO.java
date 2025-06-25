package intexsoft.practice.booking_service_kafka_dto.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class KafkaResponseRoomDTO {
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
