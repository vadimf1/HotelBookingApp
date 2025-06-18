package intexsoft.practice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class ResponseHotelDto {
    private UUID id;
    private AddressDto address;
    private DictionaryDto hotelStatus;
    private String name;
    private Integer starRating;
    private Integer averageRating;
    private String email;
    private String phoneNumber;
    private String website;
    private Set<ResponseAmenityDto> amenities;
}
