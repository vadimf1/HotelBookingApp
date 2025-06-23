package intexsoft.practice.notification_service.config.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "room.client")
@Component
@Getter
@Setter
public class RoomClientProperties {
    private String api;
}
