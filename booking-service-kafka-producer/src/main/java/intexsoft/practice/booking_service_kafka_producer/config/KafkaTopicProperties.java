package intexsoft.practice.booking_service_kafka_producer.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kafka.topics")
@Data
public class KafkaTopicProperties {

    private String bookingCreated;
    private String bookingCancelled;
    private String roomResponse;
}
