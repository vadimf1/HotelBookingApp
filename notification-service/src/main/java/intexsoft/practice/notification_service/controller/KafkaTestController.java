package intexsoft.practice.notification_service.controller;

import intexsoft.practice.kafka_producer_lib.service.KafkaProducerService;
import intexsoft.practice.notification_service.event.UserLoggedInEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
@RequiredArgsConstructor
public class KafkaTestController {

    private final KafkaProducerService kafkaMessageProducer;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody UserLoggedInEvent message) {
        kafkaMessageProducer.send("auth.events.login","", message);
        return ResponseEntity.ok("Message sent!");
    }
}
