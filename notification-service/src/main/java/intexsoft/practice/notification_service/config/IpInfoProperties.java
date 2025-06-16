package intexsoft.practice.notification_service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ipinfo")
@Getter
@Setter
public class IpInfoProperties {
    private String api;
}
