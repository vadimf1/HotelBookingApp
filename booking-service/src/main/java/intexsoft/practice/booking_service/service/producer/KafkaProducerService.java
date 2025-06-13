package intexsoft.practice.booking_service.service.producer;

import intexsoft.practice.booking_service.dto.KafkaBookingEventDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, KafkaBookingEventDTO> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, KafkaBookingEventDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendBookingEvent(KafkaBookingEventDTO eventDTO) {
        String topic = determineTopicByEventType(eventDTO.getEventType());
        kafkaTemplate.send(topic, eventDTO);
    }

    public String determineTopicByEventType(String eventType) {
        return switch (eventType) {
            case "BOOKING_CREATED" -> "booking.created";
            case "BOOKING_CANCELLED" -> "booking.cancelled";
            default -> "booking.default";
        };
    }
}
