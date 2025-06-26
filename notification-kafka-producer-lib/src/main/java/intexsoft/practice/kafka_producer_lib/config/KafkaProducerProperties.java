package intexsoft.practice.kafka_producer_lib.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kafka.topics")
@Getter
@Setter
public class KafkaProducerProperties {
    private String topic;
    private String bootstrapServers;
    private String clientId;
}

