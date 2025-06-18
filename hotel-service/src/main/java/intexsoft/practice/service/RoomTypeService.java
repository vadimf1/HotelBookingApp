package intexsoft.practice.service;

import intexsoft.practice.dto.RoomTypeDto;
import intexsoft.practice.dto.UpdateRoomTypeDto;

import java.util.List;
import java.util.UUID;

public interface RoomTypeService {
    void addRoomType(RoomTypeDto roomTypeDto);
    List<RoomTypeDto> getAllRoomTypes();
    RoomTypeDto getRoomTypeById(UUID id);
    void updateRoomType(UUID id, UpdateRoomTypeDto updateRoomTypeDto);
    void deleteRoomTypeById(UUID id);
}
