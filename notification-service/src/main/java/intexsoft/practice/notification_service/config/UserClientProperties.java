package intexsoft.practice.notification_service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "user.client")
@Getter
@Setter
public class UserClientProperties {
    private String api;
}
