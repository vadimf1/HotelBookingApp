package intexsoft.practice.booking_service.config;

import intexsoft.practice.booking_service_kafka_dto.dto.KafkaResponseRoomDTO;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class KafkaConfig {

    private final KafkaProperties kafkaProperties;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaResponseRoomDTO> kafkaResponseRoomDTOFactory(
            ConsumerFactory<String, KafkaResponseRoomDTO> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, KafkaResponseRoomDTO> factory =
                new  ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, KafkaResponseRoomDTO> consumerFactory() {
        Map<String,Object> props = new HashMap<>();
        props.put(BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        props.put(GROUP_ID_CONFIG, kafkaProperties.getGroupId());
        props.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        JsonDeserializer<KafkaResponseRoomDTO> deserializer = new JsonDeserializer<>(KafkaResponseRoomDTO.class);
        deserializer.setUseTypeMapperForKey(false);
        deserializer.addTrustedPackages("intexsoft.practice.booking_service_kafka_dto.dto");

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                deserializer
        );
    }
}
