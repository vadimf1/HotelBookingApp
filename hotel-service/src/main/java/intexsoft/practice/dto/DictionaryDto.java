package intexsoft.practice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class DictionaryDto {
    private UUID id;

    @NotBlank
    @Size(max = 50)
    private String code;

    @NotBlank(message = "Name обязателен")
    @Size(max = 100)
    private String name;
}
