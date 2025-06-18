package intexsoft.practice.notification_service;

import intexsoft.practice.notification_service.config.CountryLocaleProperties;
import intexsoft.practice.notification_service.config.IpInfoProperties;
import intexsoft.practice.notification_service.config.MailProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication()
@EnableConfigurationProperties({MailProperties.class, IpInfoProperties.class, CountryLocaleProperties.class})
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
