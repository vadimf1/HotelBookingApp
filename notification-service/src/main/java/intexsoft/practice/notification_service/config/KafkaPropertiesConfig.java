package intexsoft.practice.notification_service.config;

import intexsoft.practice.kafka_producer_lib.config.KafkaProducerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaPropertiesConfig {

    @Bean
    public KafkaProducerProperties kafkaProducerProperties() {
        KafkaProducerProperties props = new KafkaProducerProperties();
        props.setBootstrapServers("localhost:9092");
        props.setClientId("auth-service-client");
        return props;
    }
}
