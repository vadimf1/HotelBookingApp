package intexsoft.practice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ClientDto {
    private UUID id;
}
