package intexsoft.practice.controller;

import intexsoft.practice.dto.RoomTypeDto;
import intexsoft.practice.dto.UpdateRoomTypeDto;
import intexsoft.practice.service.RoomTypeService;
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
@RequestMapping("/room-types")
@RequiredArgsConstructor
public class RoomTypeController {
    private final RoomTypeService roomTypeService;

    @GetMapping
    public ResponseEntity<List<RoomTypeDto>> getAllRoomTypes() {
        return ResponseEntity.ok(roomTypeService.getAllRoomTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomTypeDto> getRoomTypeById(@PathVariable UUID id) {
        return ResponseEntity.ok(roomTypeService.getRoomTypeById(id));
    }

    @PostMapping
    public ResponseEntity<String> addRoomType(@Valid @RequestBody RoomTypeDto roomTypeDto) {
        roomTypeService.addRoomType(roomTypeDto);
        return ResponseEntity.ok("Room type added successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateRoomType(@PathVariable UUID id, @Valid @RequestBody UpdateRoomTypeDto updateRoomTypeDto) {
        roomTypeService.updateRoomType(id, updateRoomTypeDto);
        return ResponseEntity.ok("Room type updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoomType(@PathVariable("id") UUID id) {
        roomTypeService.deleteRoomTypeById(id);
        return ResponseEntity.ok("Room type deleted successfully");
    }
}

