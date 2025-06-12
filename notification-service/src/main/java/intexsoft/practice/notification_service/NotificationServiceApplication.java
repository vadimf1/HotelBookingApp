package intexsoft.practice.notification_service;

import intexsoft.practice.notification_service.config.MailProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = {
		"intexsoft.practice.notification_service",
		"intexsoft.practice.kafka_producer_lib" // <--- добавить это
})
@EnableConfigurationProperties(MailProperties.class)
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

}
