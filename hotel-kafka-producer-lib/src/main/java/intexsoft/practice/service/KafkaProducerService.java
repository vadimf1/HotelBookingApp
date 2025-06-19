package intexsoft.practice.service;

import intexsoft.practice.config.KafkaProducerProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.awt.desktop.AppEvent;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private final KafkaProducerProperties props;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(String key, AppEvent event) {
        kafkaTemplate.send(props.getTopic(), key, event);
    }
}
