package intexsoft.practice.service.impl;

import intexsoft.practice.dto.AddRoomDto;
import intexsoft.practice.dto.ResponseRoomDto;
import intexsoft.practice.dto.UpdateRoomDto;
import intexsoft.practice.exception.ServiceException;
import intexsoft.practice.mapper.RoomMapper;
import intexsoft.practice.model.Room;
import intexsoft.practice.repository.AmenityRepository;
import intexsoft.practice.repository.HotelRepository;
import intexsoft.practice.repository.RoomRepository;
import intexsoft.practice.repository.RoomStatusRepository;
import intexsoft.practice.repository.RoomTypeRepository;
import intexsoft.practice.service.RoomService;
import intexsoft.practice.util.exceptionCode.AmenityExceptionCode;
import intexsoft.practice.util.exceptionCode.HotelExceptionCode;
import intexsoft.practice.util.exceptionCode.RoomExceptionCode;
import intexsoft.practice.util.exceptionCode.RoomStatusExceptionCode;
import intexsoft.practice.util.exceptionCode.RoomTypeExceptionCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final RoomStatusRepository roomStatusRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final RoomMapper roomMapper;
    private final AmenityRepository amenityRepository;

    public void addRoom(AddRoomDto addRoomDto) {
        Room room = roomMapper.toEntity(addRoomDto);
        room.setHotel(
                hotelRepository.findById(addRoomDto.getHotelId())
                        .orElseThrow(() -> new ServiceException(HotelExceptionCode.HOTEL_NOT_FOUNT_BY_ID.getMessage() + addRoomDto.getHotelId()))

        );
        room.setRoomType(
                roomTypeRepository.findById(addRoomDto.getRoomTypeId())
                        .orElseThrow(() -> new ServiceException(RoomTypeExceptionCode.ROOM_TYPE_NOT_FOUNT_BY_ID.getMessage() + addRoomDto.getRoomTypeId()))
        );
        room.setRoomStatus(
                roomStatusRepository.findById(addRoomDto.getRoomStatusId())
                        .orElseThrow(() -> new ServiceException(RoomStatusExceptionCode.ROOM_STATUS_NOT_FOUNT_BY_ID.getMessage() + addRoomDto.getRoomStatusId()))
        );
        for (UUID amenityId : addRoomDto.getAmenityIds()) {
            room.addAmenity(
                amenityRepository.findById(amenityId)
                        .orElseThrow(() -> new ServiceException(AmenityExceptionCode.AMENITY_NOT_FOUNT_BY_ID.getMessage() + amenityId))
            );
        }
        roomRepository.save(room);
    }

    public List<ResponseRoomDto> getAllRooms() {
        return roomRepository.findAll()
                .stream()
                .map(roomMapper::toDto)
                .toList();
    }

    public ResponseRoomDto getRoomById(UUID id) {
        return roomRepository.findById(id)
                .map(roomMapper::toDto)
                .orElseThrow(() -> new ServiceException(RoomExceptionCode.ROOM_NOT_FOUNT_BY_ID.getMessage() + id));
    }

    public void updateRoom(UUID id, UpdateRoomDto updateRoomDto) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ServiceException(RoomExceptionCode.ROOM_NOT_FOUNT_BY_ID.getMessage() + id));
        room.setAmenities(new HashSet<>());
        if (updateRoomDto.getHotelId() != null) {
            room.setHotel(
                hotelRepository.findById(updateRoomDto.getHotelId())
                        .orElseThrow(() -> new ServiceException(HotelExceptionCode.HOTEL_NOT_FOUNT_BY_ID.getMessage() + updateRoomDto.getHotelId()))

            );
        }
        if (updateRoomDto.getRoomTypeId() != null) {
            room.setRoomType(
                    roomTypeRepository.findById(updateRoomDto.getRoomTypeId())
                            .orElseThrow(() -> new ServiceException(RoomTypeExceptionCode.ROOM_TYPE_NOT_FOUNT_BY_ID.getMessage() + updateRoomDto.getRoomTypeId()))
            );
        }
        if (updateRoomDto.getRoomStatusId() != null) {
            room.setRoomStatus(
                roomStatusRepository.findById(updateRoomDto.getRoomStatusId())
                        .orElseThrow(() -> new ServiceException(RoomStatusExceptionCode.ROOM_STATUS_NOT_FOUNT_BY_ID.getMessage() + updateRoomDto.getRoomStatusId()))
            );
        }
        for (UUID amenityId : updateRoomDto.getAmenityIds()) {
            room.addAmenity(
                    amenityRepository.findById(amenityId)
                            .orElseThrow(() -> new ServiceException(AmenityExceptionCode.AMENITY_NOT_FOUNT_BY_ID.getMessage() + amenityId))
            );
        }
        roomMapper.updateEntityFromDto(updateRoomDto, room);
        roomRepository.save(room);
    }

    public void deleteRoomById(UUID id) {
        roomRepository.deleteById(id);
    }
}
