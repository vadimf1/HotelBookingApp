package intexsoft.practice.kafka_producer_lib.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(String topic, String key, Object message) {
        kafkaTemplate.send(topic, key, message);
    }
}
