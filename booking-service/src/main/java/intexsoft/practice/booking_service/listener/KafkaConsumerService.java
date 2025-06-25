package intexsoft.practice.booking_service.listener;

import intexsoft.practice.booking_service.service.RoomService;
import intexsoft.practice.booking_service_kafka_dto.dto.KafkaResponseRoomDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final RoomService roomService;

    @KafkaListener(topics = "#{kafkaTopicProperties.roomResponse}", containerFactory = "kafkaResponseRoomDTOFactory")
    public void handleRoomIdRequest(KafkaResponseRoomDTO responseDTO) {
        log.warn("Получена комната: {}", responseDTO.getId());

        roomService.saveRoom(responseDTO);
    }
}
