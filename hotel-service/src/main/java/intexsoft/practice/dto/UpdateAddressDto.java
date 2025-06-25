package intexsoft.practice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateAddressDto {
    private String country;
    private String state;
    private String city;
    private String street;
    private String postalCode;
}
