package intexsoft.practice.notification_service.config.client;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "user.client")
@Component
@Getter
@Setter
public class UserClientProperties {
    private String api;
}
