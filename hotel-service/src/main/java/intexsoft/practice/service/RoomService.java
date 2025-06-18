package intexsoft.practice.service;

import intexsoft.practice.dto.AddRoomDto;
import intexsoft.practice.dto.ResponseRoomDto;
import intexsoft.practice.dto.UpdateRoomDto;

import java.util.List;
import java.util.UUID;

public interface RoomService {
    void addRoom(AddRoomDto addRoomDto);
    List<ResponseRoomDto> getAllRooms();
    ResponseRoomDto getRoomById(UUID id);
    void updateRoom(UUID id, UpdateRoomDto updateRoomDto);
    void deleteRoomById(UUID id);
}
