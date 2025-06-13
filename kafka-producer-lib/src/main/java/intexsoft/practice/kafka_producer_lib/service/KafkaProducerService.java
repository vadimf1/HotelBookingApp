package intexsoft.practice.kafka_producer_lib.service;

import intexsoft.practice.dto.AppEvent;
import intexsoft.practice.kafka_producer_lib.config.KafkaProducerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaProducerProperties props;
    private final KafkaTemplate<String, Object> kafkaTemplate;


    public void send(String key, AppEvent event) {
        kafkaTemplate.send(props.getTopic(), key, event);
    }
}
