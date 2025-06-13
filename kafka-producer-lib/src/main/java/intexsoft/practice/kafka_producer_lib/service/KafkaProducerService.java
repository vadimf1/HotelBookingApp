package intexsoft.practice.kafka_producer_lib.service;

import intexsoft.practice.dto.AppEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(String topic, String key, AppEvent event) {
        kafkaTemplate.send(event.topic(), key, event);
    }
}
