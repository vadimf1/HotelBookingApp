package intexsoft.practice.booking_service.service;

import intexsoft.practice.booking_service.mapper.RoomMapper;
import intexsoft.practice.booking_service.model.Room;
import intexsoft.practice.booking_service.repository.RoomRepository;
import intexsoft.practice.booking_service_kafka_dto.dto.KafkaResponseRoomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Transactional
    public void saveRoom(KafkaResponseRoomDTO responseDTO) {
        Room room = roomMapper.toEntity(responseDTO);

        roomRepository.save(room);
    }
}
