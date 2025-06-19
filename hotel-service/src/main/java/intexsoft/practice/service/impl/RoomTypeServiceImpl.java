package intexsoft.practice.service.impl;

import intexsoft.practice.dto.RoomTypeDto;
import intexsoft.practice.dto.UpdateRoomTypeDto;
import intexsoft.practice.exception.ServiceException;
import intexsoft.practice.mapper.RoomTypeMapper;
import intexsoft.practice.model.RoomType;
import intexsoft.practice.repository.RoomTypeRepository;
import intexsoft.practice.service.RoomTypeService;
import intexsoft.practice.exception.code.RoomTypeExceptionCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomTypeServiceImpl implements RoomTypeService {
    private final RoomTypeRepository roomTypeRepository;
    private final RoomTypeMapper roomTypeMapper;

    public RoomTypeDto addRoomType(RoomTypeDto roomTypeDto) {
        if (roomTypeDto.getId() != null) {
            throw new ServiceException(RoomTypeExceptionCode.ID_FIELD_EXPECTED_NULL.getMessage());
        }
        RoomType savedRoomType = roomTypeRepository.save(roomTypeMapper.toEntity(roomTypeDto));
        return roomTypeMapper.toDto(savedRoomType);
    }

    public List<RoomTypeDto> getAllRoomTypes() {
        return roomTypeRepository.findAll()
                .stream()
                .map(roomTypeMapper::toDto)
                .toList();
    }

    public RoomTypeDto getRoomTypeById(UUID id) {
        return roomTypeRepository.findById(id)
                .map(roomTypeMapper::toDto)
                .orElseThrow(() -> new ServiceException(RoomTypeExceptionCode.ROOM_TYPE_NOT_FOUNT_BY_ID.getMessage() + id));
    }

    public RoomTypeDto updateRoomType(UUID id, UpdateRoomTypeDto updateRoomTypeDto) {
        RoomType roomType = roomTypeRepository.findById(id)
                .orElseThrow(() -> new ServiceException(RoomTypeExceptionCode.ROOM_TYPE_NOT_FOUNT_BY_ID.getMessage() + id));

        roomTypeMapper.updateEntityFromDto(updateRoomTypeDto, roomType);
        RoomType updatedRoomType = roomTypeRepository.save(roomType);
        return roomTypeMapper.toDto(updatedRoomType);
    }

    public void deleteRoomTypeById(UUID id) {
        roomTypeRepository.deleteById(id);
    }
}
