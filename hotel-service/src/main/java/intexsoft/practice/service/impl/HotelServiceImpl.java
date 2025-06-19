package intexsoft.practice.service.impl;

import intexsoft.practice.dto.AddHotelDto;
import intexsoft.practice.dto.ResponseHotelDto;
import intexsoft.practice.dto.UpdateHotelDto;
import intexsoft.practice.exception.ServiceException;
import intexsoft.practice.mapper.HotelMapper;
import intexsoft.practice.model.Hotel;
import intexsoft.practice.repository.AddressRepository;
import intexsoft.practice.repository.AmenityRepository;
import intexsoft.practice.repository.HotelRepository;
import intexsoft.practice.repository.HotelStatusRepository;
import intexsoft.practice.service.HotelService;
import intexsoft.practice.exception.code.AddressExceptionCode;
import intexsoft.practice.exception.code.AmenityExceptionCode;
import intexsoft.practice.exception.code.HotelExceptionCode;
import intexsoft.practice.exception.code.HotelStatusExceptionCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final HotelRepository hotelRepository;
    private final AddressRepository addressRepository;
    private final HotelStatusRepository hotelStatusRepository;
    private final AmenityRepository amenityRepository;
    private final HotelMapper hotelMapper;

    public ResponseHotelDto addHotel(AddHotelDto addHotelDto) {
        Hotel hotel = hotelMapper.toEntity(addHotelDto);
        hotel.setAddress(
                addressRepository.findById(addHotelDto.getAddressId())
                        .orElseThrow(() -> new ServiceException(AddressExceptionCode.ADDRESS_NOT_FOUNT_BY_ID.getMessage() + addHotelDto.getAddressId()))

        );
        hotel.setHotelStatus(
                hotelStatusRepository.findById(addHotelDto.getHotelStatusId())
                        .orElseThrow(() -> new ServiceException(HotelStatusExceptionCode.HOTEL_STATUS_NOT_FOUNT_BY_ID.getMessage() + addHotelDto.getHotelStatusId()))
        );
        for (UUID amenityId : addHotelDto.getAmenityIds()) {
            hotel.addAmenity(
                    amenityRepository.findById(amenityId)
                            .orElseThrow(() -> new ServiceException(AmenityExceptionCode.AMENITY_NOT_FOUNT_BY_ID.getMessage() + amenityId))
            );
        }
        Hotel savedHotel = hotelRepository.save(hotel);
        return hotelMapper.toDto(savedHotel);
    }

    public List<ResponseHotelDto> getAllHotels() {
        return hotelRepository.findAll()
                .stream()
                .map(hotelMapper::toDto)
                .toList();
    }

    public ResponseHotelDto getHotelById(UUID id) {
        return hotelRepository.findById(id)
                .map(hotelMapper::toDto)
                .orElseThrow(() -> new ServiceException(HotelExceptionCode.HOTEL_NOT_FOUNT_BY_ID.getMessage() + id));
    }

    public ResponseHotelDto updateHotel(UUID id, UpdateHotelDto updateHotelDto) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ServiceException(HotelExceptionCode.HOTEL_NOT_FOUNT_BY_ID.getMessage() + id));
        hotel.setAmenities(new HashSet<>());
        if (updateHotelDto.getAddressId() != null) {
            hotel.setAddress(
                    addressRepository.findById(updateHotelDto.getAddressId())
                            .orElseThrow(() -> new ServiceException(AddressExceptionCode.ADDRESS_NOT_FOUNT_BY_ID.getMessage() + updateHotelDto.getAddressId()))

            );
        }
        if (updateHotelDto.getHotelStatusId() != null) {
            hotel.setHotelStatus(
                    hotelStatusRepository.findById(updateHotelDto.getHotelStatusId())
                            .orElseThrow(() -> new ServiceException(HotelStatusExceptionCode.HOTEL_STATUS_NOT_FOUNT_BY_ID.getMessage() + updateHotelDto.getHotelStatusId()))
            );
        }
        if (updateHotelDto.getAmenityIds() != null) {
            for (UUID amenityId : updateHotelDto.getAmenityIds()) {
                hotel.addAmenity(
                        amenityRepository.findById(amenityId)
                                .orElseThrow(() -> new ServiceException(AmenityExceptionCode.AMENITY_NOT_FOUNT_BY_ID.getMessage() + amenityId))
                );
            }
        }

        hotelMapper.updateEntityFromDto(updateHotelDto, hotel);
        Hotel updatedHotel = hotelRepository.save(hotel);
        return hotelMapper.toDto(updatedHotel);
    }

    public void deleteHotelById(UUID id) {
        hotelRepository.deleteById(id);
    }
}
