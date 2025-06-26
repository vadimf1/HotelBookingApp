package intexsoft.practice.service.impl;

import intexsoft.practice.dto.DictionaryDto;
import intexsoft.practice.dto.UpdateDictionaryDto;
import intexsoft.practice.exception.ServiceException;
import intexsoft.practice.mapper.AmenityStatusMapper;
import intexsoft.practice.model.AmenityStatus;
import intexsoft.practice.repository.AmenityStatusRepository;
import intexsoft.practice.service.AmenityStatusService;
import intexsoft.practice.exception.code.AmenityStatusExceptionCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AmenityStatusServiceImpl implements AmenityStatusService {
    private final AmenityStatusRepository amenityStatusRepository;
    private final AmenityStatusMapper amenityStatusMapper;

    public DictionaryDto addAmenityStatus(DictionaryDto amenityStatusDto) {
        if (amenityStatusDto.getId() != null) {
            throw new ServiceException(AmenityStatusExceptionCode.ID_FIELD_EXPECTED_NULL.getMessage());
        }
        AmenityStatus savedAmenityStatus = amenityStatusRepository.save(amenityStatusMapper.toEntity(amenityStatusDto));
        return amenityStatusMapper.toDto(savedAmenityStatus);
    }

    public List<DictionaryDto> getAllAmenityStatuses() {
        return amenityStatusRepository.findAll()
                .stream()
                .map(amenityStatusMapper::toDto)
                .toList();
    }

    public DictionaryDto getAmenityStatusById(UUID id) {
        return amenityStatusRepository.findById(id)
                .map(amenityStatusMapper::toDto)
                .orElseThrow(() -> new ServiceException(AmenityStatusExceptionCode.AMENITY_STATUS_NOT_FOUNT_BY_ID.getMessage() + id));
    }

    public DictionaryDto updateAmenityStatus(UUID id, UpdateDictionaryDto updateAmenityStatusDto) {
        AmenityStatus amenityStatus = amenityStatusRepository.findById(id)
                .orElseThrow(() -> new ServiceException(AmenityStatusExceptionCode.AMENITY_STATUS_NOT_FOUNT_BY_ID.getMessage() + id));

        amenityStatusMapper.updateEntityFromDto(updateAmenityStatusDto, amenityStatus);
        AmenityStatus updatedAmenityStatus = amenityStatusRepository.save(amenityStatus);
        return amenityStatusMapper.toDto(updatedAmenityStatus);
    }

    public void deleteAmenityStatusById(UUID id) {
        amenityStatusRepository.deleteById(id);
    }
}

