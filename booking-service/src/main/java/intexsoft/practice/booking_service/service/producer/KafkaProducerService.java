package intexsoft.practice.booking_service.service.producer;

import intexsoft.practice.booking_service.config.properties.KafkaTopicProperties;
import intexsoft.practice.booking_service.dto.KafkaBookingEventDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, KafkaBookingEventDTO> kafkaTemplate;
    private final KafkaTopicProperties kafkaTopicProperties;

    public KafkaProducerService(
            KafkaTemplate<String, KafkaBookingEventDTO> kafkaTemplate,
            KafkaTopicProperties kafkaTopicProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopicProperties = kafkaTopicProperties;
    }

    public void sendBookingEvent(KafkaBookingEventDTO eventDTO) {
        String topic;

        switch (eventDTO.getEventType()) {
            case BOOKING_CREATED ->  topic = kafkaTopicProperties.getBookingCreated();
            case BOOKING_CANCELLED ->  topic = kafkaTopicProperties.getBookingCancelled();
            default -> throw new IllegalArgumentException("Неизвестный тип события: " +  eventDTO.getEventType());
        }

        kafkaTemplate.send(topic, eventDTO);
    }
}
