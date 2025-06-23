package intexsoft.practice.listener;

import intexsoft.practice.dto.KafkaRequestRoomDto;
import intexsoft.practice.service.RoomKafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomEventListener {
    private final RoomKafkaProducerService roomKafkaProducerService;

    @KafkaListener(topics = "${hotel.kafka.topic.request}", groupId = "${spring.kafka.consumer.group-id}")
    public void handleRoomRequest(KafkaRequestRoomDto request) {
        roomKafkaProducerService.sendRoomInfo(request);
    }
}
