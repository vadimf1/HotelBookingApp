package intexsoft.practice.service;

import intexsoft.practice.dto.DictionaryDto;
import intexsoft.practice.dto.UpdateDictionaryDto;

import java.util.List;
import java.util.UUID;

public interface AmenityStatusService {
    DictionaryDto addAmenityStatus(DictionaryDto dictionaryDto);
    List<DictionaryDto> getAllAmenityStatuses();
    DictionaryDto getAmenityStatusById(UUID id);
    DictionaryDto updateAmenityStatus(UUID id, UpdateDictionaryDto updateDictionaryDto);
    void deleteAmenityStatusById(UUID id);
}
