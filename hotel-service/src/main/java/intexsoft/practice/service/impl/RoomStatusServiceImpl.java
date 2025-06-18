package intexsoft.practice.service.impl;

import intexsoft.practice.dto.DictionaryDto;
import intexsoft.practice.dto.UpdateDictionaryDto;
import intexsoft.practice.exception.ServiceException;
import intexsoft.practice.mapper.RoomStatusMapper;
import intexsoft.practice.model.RoomStatus;
import intexsoft.practice.repository.RoomStatusRepository;
import intexsoft.practice.service.RoomStatusService;
import intexsoft.practice.util.exceptionCode.RoomStatusExceptionCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomStatusServiceImpl implements RoomStatusService {
    private final RoomStatusRepository roomStatusRepository;
    private final RoomStatusMapper roomStatusMapper;

    public void addRoomStatus(DictionaryDto roomStatusDto) {
        if (roomStatusDto.getId() != null) {
            throw new ServiceException(RoomStatusExceptionCode.ID_FIELD_EXPECTED_NULL.getMessage());
        }
        roomStatusRepository.save(roomStatusMapper.toEntity(roomStatusDto));
    }

    public List<DictionaryDto> getAllRoomStatuses() {
        return roomStatusRepository.findAll()
                .stream()
                .map(roomStatusMapper::toDto)
                .toList();
    }

    public DictionaryDto getRoomStatusById(UUID id) {
        return roomStatusRepository.findById(id)
                .map(roomStatusMapper::toDto)
                .orElseThrow(() -> new ServiceException(RoomStatusExceptionCode.ROOM_STATUS_NOT_FOUNT_BY_ID.getMessage() + id));
    }

    public void updateRoomStatus(UUID id, UpdateDictionaryDto updateRoomStatusDto) {
        RoomStatus roomStatus = roomStatusRepository.findById(id)
                .orElseThrow(() -> new ServiceException(RoomStatusExceptionCode.ROOM_STATUS_NOT_FOUNT_BY_ID.getMessage() + id));

        roomStatusMapper.updateEntityFromDto(updateRoomStatusDto, roomStatus);
        roomStatusRepository.save(roomStatus);
    }

    public void deleteRoomStatusById(UUID id) {
        roomStatusRepository.deleteById(id);
    }
}