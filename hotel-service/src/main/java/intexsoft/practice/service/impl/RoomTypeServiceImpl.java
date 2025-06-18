package intexsoft.practice.service.impl;

import intexsoft.practice.dto.RoomTypeDto;
import intexsoft.practice.dto.UpdateRoomTypeDto;
import intexsoft.practice.exception.ServiceException;
import intexsoft.practice.mapper.RoomTypeMapper;
import intexsoft.practice.model.RoomType;
import intexsoft.practice.repository.RoomTypeRepository;
import intexsoft.practice.service.RoomTypeService;
import intexsoft.practice.util.exceptionCode.RoomTypeExceptionCode;
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

    public void addRoomType(RoomTypeDto roomTypeDto) {
        if (roomTypeDto.getId() != null) {
            throw new ServiceException(RoomTypeExceptionCode.ID_FIELD_EXPECTED_NULL.getMessage());
        }
        roomTypeRepository.save(roomTypeMapper.toEntity(roomTypeDto));
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

    public void updateRoomType(UUID id, UpdateRoomTypeDto updateRoomTypeDto) {
        RoomType roomType = roomTypeRepository.findById(id)
                .orElseThrow(() -> new ServiceException(RoomTypeExceptionCode.ROOM_TYPE_NOT_FOUNT_BY_ID.getMessage() + id));

        roomTypeMapper.updateEntityFromDto(updateRoomTypeDto, roomType);
        roomTypeRepository.save(roomType);
    }

    public void deleteRoomTypeById(UUID id) {
        roomTypeRepository.deleteById(id);
    }
}
