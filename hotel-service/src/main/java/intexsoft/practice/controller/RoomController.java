package intexsoft.practice.controller;

import intexsoft.practice.dto.AddRoomDto;
import intexsoft.practice.dto.ResponseRoomDto;
import intexsoft.practice.dto.UpdateRoomDto;
import intexsoft.practice.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<List<ResponseRoomDto>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseRoomDto> getRoomById(@PathVariable UUID id) {
        return ResponseEntity.ok(roomService.getRoomById(id));
    }

    @PostMapping
    public ResponseEntity<ResponseRoomDto> addRoom(@Valid @RequestBody AddRoomDto addRoomDto) {
        ResponseRoomDto responseRoomDto = roomService.addRoom(addRoomDto);
        return ResponseEntity.ok(responseRoomDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseRoomDto> updateRoom(@PathVariable UUID id, @Valid @RequestBody UpdateRoomDto updateRoomDto) {
        ResponseRoomDto responseRoomDto = roomService.updateRoom(id, updateRoomDto);
        return ResponseEntity.ok(responseRoomDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable("id") UUID id) {
        roomService.deleteRoomById(id);
        return ResponseEntity.ok("Room deleted successfully");
    }
}
