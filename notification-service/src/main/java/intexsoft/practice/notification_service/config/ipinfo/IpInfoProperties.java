package intexsoft.practice.notification_service.config.ipinfo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "ipinfo")
@Component
@Getter
@Setter
public class IpInfoProperties {
    private String api;
}
