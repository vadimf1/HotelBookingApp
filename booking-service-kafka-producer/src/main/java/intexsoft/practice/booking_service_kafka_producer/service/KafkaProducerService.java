package intexsoft.practice.booking_service_kafka_producer.service;

import intexsoft.practice.booking_service_kafka_producer.config.KafkaTopicProperties;
import intexsoft.practice.booking_service_kafka_dto.dto.KafkaBookingEventDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, KafkaBookingEventDTO> kafkaTemplate;
    private final KafkaTopicProperties kafkaTopicProperties;

    public void sendBookingEvent(KafkaBookingEventDTO eventDTO) {
        String topic = switch (eventDTO.getEventType()) {
            case BOOKING_CREATED -> kafkaTopicProperties.getBookingCreated();
            case BOOKING_CANCELLED -> kafkaTopicProperties.getBookingCancelled();
            default -> throw new IllegalArgumentException("Неизвестный тип события: " +  eventDTO.getEventType());
        };

        kafkaTemplate.send(topic, eventDTO);
    }
}
