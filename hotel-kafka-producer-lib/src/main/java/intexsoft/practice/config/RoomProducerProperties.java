package intexsoft.practice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class RoomProducerProperties {
    private String topic;
    private String bootstrapServers;
    private String clientId;
}
