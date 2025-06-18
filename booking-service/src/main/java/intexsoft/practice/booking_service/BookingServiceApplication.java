package intexsoft.practice.booking_service;

import intexsoft.practice.booking_service_kafka_producer.config.KafkaTopicProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
		"intexsoft.practice.booking_service",
		"intexsoft.practice.booking_service_kafka_producer"
})
public class BookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingServiceApplication.class, args);
	}

}
