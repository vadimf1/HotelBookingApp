package intexsoft.practice.service;

import intexsoft.practice.dto.KafkaResponseRoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${hotel.kafka.topic.response}")
    private String topic;

    public void sendRoom(String key, KafkaResponseRoomDto responseRoomDto) {
        kafkaTemplate.send(topic, key, responseRoomDto);
    }
}
