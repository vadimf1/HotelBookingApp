package intexsoft.practice.service;

import intexsoft.practice.config.KafkaProducerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaProducerProperties props;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendRoom(String key, RoomDto roomDto) {
        kafkaTemplate.send(props.getTopic(), key, roomDto);
    }
}
