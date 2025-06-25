package intexsoft.practice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AddressDto {
    private UUID id;

    @NotBlank
    @Size(max = 50)
    private String country;

    @Size(max = 100)
    private String state;

    @NotBlank
    @Size(max = 50)
    private String city;

    @NotBlank
    @Size(max = 50)
    private String street;

    @NotBlank
    @Size(max = 10)
    private String postalCode;
}
