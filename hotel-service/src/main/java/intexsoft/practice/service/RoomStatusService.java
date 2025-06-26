package intexsoft.practice.service;

import intexsoft.practice.dto.DictionaryDto;
import intexsoft.practice.dto.UpdateDictionaryDto;

import java.util.List;
import java.util.UUID;

public interface RoomStatusService {
    DictionaryDto addRoomStatus(DictionaryDto dictionaryDto);
    List<DictionaryDto> getAllRoomStatuses();
    DictionaryDto getRoomStatusById(UUID id);
    DictionaryDto updateRoomStatus(UUID id, UpdateDictionaryDto updateDictionaryDto);
    void deleteRoomStatusById(UUID id);
}
