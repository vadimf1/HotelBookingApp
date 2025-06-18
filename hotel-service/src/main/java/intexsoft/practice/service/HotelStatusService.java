package intexsoft.practice.service;

import intexsoft.practice.dto.DictionaryDto;
import intexsoft.practice.dto.UpdateDictionaryDto;

import java.util.List;
import java.util.UUID;

public interface HotelStatusService {
    void addHotelStatus(DictionaryDto dictionaryDto);
    List<DictionaryDto> getAllHotelStatuses();
    DictionaryDto getHotelStatusById(UUID id);
    void updateHotelStatus(UUID id, UpdateDictionaryDto updateDictionaryDto);
    void deleteHotelStatusById(UUID id);
}