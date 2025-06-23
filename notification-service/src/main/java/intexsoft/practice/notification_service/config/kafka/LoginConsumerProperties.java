package intexsoft.practice.notification_service.config.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "consumer.login")
@Component
@Getter
@Setter
public class LoginConsumerProperties extends BaseConsumerProperties {
}
