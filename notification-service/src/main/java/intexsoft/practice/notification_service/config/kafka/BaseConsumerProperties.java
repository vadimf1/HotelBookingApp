package intexsoft.practice.notification_service.config.kafka;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseConsumerProperties {
    private String bootstrapServers;
    private String groupId;
}
