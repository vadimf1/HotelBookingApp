package intexsoft.practice.booking_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
		"intexsoft.practice.booking_service",
		"intexsoft.practice.booking_service_kafka_producer",
		"intexsoft.practice.booking_service_kafka_dto"
})
public class BookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingServiceApplication.class, args);
	}

}
