package intexsoft.practice.booking_service.config;

import intexsoft.practice.booking_service_kafka_dto.dto.KafkaRoomIdResponseDTO;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

@Configuration
@EnableKafka
public class KafkaConfig {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaRoomIdResponseDTO> kafkaRoomIdResponseDTOFactory(
            ConsumerFactory<String, KafkaRoomIdResponseDTO> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, KafkaRoomIdResponseDTO> factory =
                new  ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, KafkaRoomIdResponseDTO> consumerFactory() {
        Map<String,Object> props = new HashMap<>();
        props.put(BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(GROUP_ID_CONFIG, "booking-group");
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        JsonDeserializer<KafkaRoomIdResponseDTO> deserializer = new JsonDeserializer<>(KafkaRoomIdResponseDTO.class);
        deserializer.setUseTypeMapperForKey(false);
        deserializer.addTrustedPackages("intexsoft.practice.booking_service_kafka_dto.dto");

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                deserializer
        );
    }
}
