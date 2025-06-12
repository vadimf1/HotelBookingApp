package intexsoft.practice.kafka_producer_lib.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kafka.producer")
@Getter
@Setter
public class KafkaProducerProperties {
    private String bootstrapServers;
    private String clientId = "default-producer";
}