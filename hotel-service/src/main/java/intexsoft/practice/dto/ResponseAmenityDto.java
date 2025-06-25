package intexsoft.practice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseAmenityDto {
    private DictionaryDto amenityStatus;
    private String name;
    private String description;
    private Boolean isFree;
}
