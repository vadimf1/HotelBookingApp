package intexsoft.practice.service.impl;

import intexsoft.practice.dto.KafkaResponseRoomDto;
import intexsoft.practice.exception.ServiceException;
import intexsoft.practice.exception.code.RoomExceptionCode;
import intexsoft.practice.mapper.RoomKafkaMapper;
import intexsoft.practice.model.Amenity;
import intexsoft.practice.model.Room;
import intexsoft.practice.repository.RoomRepository;
import intexsoft.practice.service.RoomKafkaProducerService;
import intexsoft.practice.dto.KafkaRequestRoomDto;
import intexsoft.practice.service.KafkaProducerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomKafkaProducerServiceImpl implements RoomKafkaProducerService {

    private final KafkaProducerService kafkaProducerService;
    private final RoomRepository roomRepository;
    private final RoomKafkaMapper roomKafkaMapper;

    @Transactional
    public void sendRoomInfo(KafkaRequestRoomDto kafkaRequestRoomDto) {
        kafkaProducerService.sendRoom(kafkaRequestRoomDto.getRoomId().toString(), getRoomById(kafkaRequestRoomDto.getRoomId()));
    }

    public KafkaResponseRoomDto getRoomById(UUID id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ServiceException(RoomExceptionCode.ROOM_NOT_FOUNT_BY_ID.getMessage() + id));

        KafkaResponseRoomDto kafkaResponseRoomDto = roomKafkaMapper.toDto(room);
        kafkaResponseRoomDto.setHotelId(room.getHotel().getId());
        kafkaResponseRoomDto.setRoomTypeId(room.getRoomType().getId());
        kafkaResponseRoomDto.setRoomStatusId(room.getRoomStatus().getId());
        List<UUID> amenityIds = room.getAmenities().stream().map(Amenity::getId).toList();
        kafkaResponseRoomDto.setAmenityIds(amenityIds);
        return kafkaResponseRoomDto;
    }
}
