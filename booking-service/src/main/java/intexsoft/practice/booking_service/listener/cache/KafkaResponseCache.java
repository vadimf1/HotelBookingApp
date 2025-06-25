package intexsoft.practice.booking_service.listener.cache;

import intexsoft.practice.booking_service.dto.RoomAvailabilityDTO;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@Getter
public class KafkaResponseCache {

    // Используется только для проверки что результат дошел и верен
    private final Map<UUID, RoomAvailabilityDTO> cache = new HashMap<>();
}
