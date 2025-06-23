package intexsoft.practice.notification_service.config.mail;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.mail")
@Component
@Getter
@Setter
public class MailProperties {
    private String host;
    private int port;
    private String username;
    private String password;
}
