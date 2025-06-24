package intexsoft.practice.service;

import intexsoft.practice.dto.KafkaRequestRoomDto;

public interface RoomKafkaProducerService {
    void sendRoomInfo(KafkaRequestRoomDto requestRoomDto);
}
