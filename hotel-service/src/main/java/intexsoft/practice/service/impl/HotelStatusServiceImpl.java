package intexsoft.practice.service.impl;

import intexsoft.practice.dto.DictionaryDto;
import intexsoft.practice.dto.UpdateDictionaryDto;
import intexsoft.practice.exception.ServiceException;
import intexsoft.practice.mapper.HotelStatusMapper;
import intexsoft.practice.model.HotelStatus;
import intexsoft.practice.repository.HotelStatusRepository;
import intexsoft.practice.service.HotelStatusService;
import intexsoft.practice.exception.code.HotelStatusExceptionCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class HotelStatusServiceImpl implements HotelStatusService {
    private final HotelStatusRepository hotelStatusRepository;
    private final HotelStatusMapper hotelStatusMapper;

    public DictionaryDto addHotelStatus(DictionaryDto hotelStatusDto) {
        if (hotelStatusDto.getId() != null) {
            throw new ServiceException(HotelStatusExceptionCode.ID_FIELD_EXPECTED_NULL.getMessage());
        }
        HotelStatus savedHotelStatus = hotelStatusRepository.save(hotelStatusMapper.toEntity(hotelStatusDto));
        return hotelStatusMapper.toDto(savedHotelStatus);
    }

    public List<DictionaryDto> getAllHotelStatuses() {
        return hotelStatusRepository.findAll()
                .stream()
                .map(hotelStatusMapper::toDto)
                .toList();
    }

    public DictionaryDto getHotelStatusById(UUID id) {
        return hotelStatusRepository.findById(id)
                .map(hotelStatusMapper::toDto)
                .orElseThrow(() -> new ServiceException(HotelStatusExceptionCode.HOTEL_STATUS_NOT_FOUNT_BY_ID.getMessage() + id));
    }

    public DictionaryDto updateHotelStatus(UUID id, UpdateDictionaryDto updateHotelStatusDto) {
        HotelStatus hotelStatus = hotelStatusRepository.findById(id)
                .orElseThrow(() -> new ServiceException(HotelStatusExceptionCode.HOTEL_STATUS_NOT_FOUNT_BY_ID.getMessage() + id));

        hotelStatusMapper.updateEntityFromDto(updateHotelStatusDto, hotelStatus);
        HotelStatus updatedHotelStatus = hotelStatusRepository.save(hotelStatus);
        return hotelStatusMapper.toDto(updatedHotelStatus);
    }

    public void deleteHotelStatusById(UUID id) {
        hotelStatusRepository.deleteById(id);
    }
}