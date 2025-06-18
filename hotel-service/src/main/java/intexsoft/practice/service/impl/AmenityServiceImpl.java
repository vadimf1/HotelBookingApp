package intexsoft.practice.service.impl;

import intexsoft.practice.dto.AddAmenityDto;
import intexsoft.practice.dto.ResponseAmenityDto;
import intexsoft.practice.dto.UpdateAmenityDto;
import intexsoft.practice.exception.ServiceException;
import intexsoft.practice.mapper.AmenityMapper;
import intexsoft.practice.model.Amenity;
import intexsoft.practice.repository.AmenityRepository;
import intexsoft.practice.repository.AmenityStatusRepository;
import intexsoft.practice.service.AmenityService;
import intexsoft.practice.util.exceptionCode.AmenityExceptionCode;
import intexsoft.practice.util.exceptionCode.AmenityStatusExceptionCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AmenityServiceImpl implements AmenityService {
    private final AmenityRepository amenityRepository;
    private final AmenityStatusRepository amenityStatusRepository;
    private final AmenityMapper amenityMapper;

    public void addAmenity(AddAmenityDto addAmenityDto) {
        Amenity amenity = amenityMapper.toEntity(addAmenityDto);

        amenity.setAmenityStatus(
                amenityStatusRepository.findById(addAmenityDto.getAmenityStatusId())
                        .orElseThrow(() -> new ServiceException(AmenityStatusExceptionCode.AMENITY_STATUS_NOT_FOUNT_BY_ID.getMessage() + addAmenityDto.getAmenityStatusId()))
        );
        amenityRepository.save(amenity);
    }

    public List<ResponseAmenityDto> getAllAmenities() {
        return amenityRepository.findAll()
                .stream()
                .map(amenityMapper::toDto)
                .toList();
    }

    public ResponseAmenityDto getAmenityById(UUID id) {
        return amenityRepository.findById(id)
                .map(amenityMapper::toDto)
                .orElseThrow(() -> new ServiceException(AmenityExceptionCode.AMENITY_NOT_FOUNT_BY_ID.getMessage() + id));
    }

    public void updateAmenity(UUID id, UpdateAmenityDto updateAmenityDto) {
        Amenity amenity = amenityRepository.findById(id)
                .orElseThrow(() -> new ServiceException(AmenityExceptionCode.AMENITY_NOT_FOUNT_BY_ID.getMessage() + id));

        if (updateAmenityDto.getAmenityStatusId() != null) {
            amenity.setAmenityStatus(
                    amenityStatusRepository.findById(updateAmenityDto.getAmenityStatusId())
                            .orElseThrow(() -> new ServiceException(AmenityStatusExceptionCode.AMENITY_STATUS_NOT_FOUNT_BY_ID.getMessage() + updateAmenityDto.getAmenityStatusId()))
            );
        }

        amenityMapper.updateEntityFromDto(updateAmenityDto, amenity);
        amenityRepository.save(amenity);
    }

    public void deleteAmenityById(UUID id) {
        amenityRepository.deleteById(id);
    }
}
