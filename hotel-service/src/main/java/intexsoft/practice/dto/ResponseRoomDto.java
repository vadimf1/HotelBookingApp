package intexsoft.practice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class ResponseRoomDto {
    private UUID id;
    private ResponseHotelDto hotel;
    private DictionaryDto roomType;
    private DictionaryDto roomStatus;
    private Integer roomNumber;
    private Double pricePerDay;
    private Integer floor;
    private String description;
    private Set<ResponseAmenityDto> amenities;
}
