package intexsoft.practice.service.impl;

import intexsoft.practice.dto.DictionaryDto;
import intexsoft.practice.dto.UpdateDictionaryDto;
import intexsoft.practice.exception.ServiceException;
import intexsoft.practice.mapper.HotelStatusMapper;
import intexsoft.practice.model.HotelStatus;
import intexsoft.practice.repository.HotelStatusRepository;
import intexsoft.practice.service.HotelStatusService;
import intexsoft.practice.util.exceptionCode.HotelStatusExceptionCode;
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

    public void addHotelStatus(DictionaryDto hotelStatusDto) {
        if (hotelStatusDto.getId() != null) {
            throw new ServiceException(HotelStatusExceptionCode.ID_FIELD_EXPECTED_NULL.getMessage());
        }
        hotelStatusRepository.save(hotelStatusMapper.toEntity(hotelStatusDto));
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

    public void updateHotelStatus(UUID id, UpdateDictionaryDto updateHotelStatusDto) {
        HotelStatus hotelStatus = hotelStatusRepository.findById(id)
                .orElseThrow(() -> new ServiceException(HotelStatusExceptionCode.HOTEL_STATUS_NOT_FOUNT_BY_ID.getMessage() + id));

        hotelStatusMapper.updateEntityFromDto(updateHotelStatusDto, hotelStatus);
        hotelStatusRepository.save(hotelStatus);
    }

    public void deleteHotelStatusById(UUID id) {
        hotelStatusRepository.deleteById(id);
    }
}