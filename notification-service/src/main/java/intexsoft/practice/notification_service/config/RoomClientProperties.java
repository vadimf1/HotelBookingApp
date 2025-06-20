package intexsoft.practice.notification_service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "room.client")
@Getter
@Setter
public class RoomClientProperties {
    private String api;
}
