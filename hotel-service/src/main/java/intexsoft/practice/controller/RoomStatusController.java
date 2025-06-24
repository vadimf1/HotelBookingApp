package intexsoft.practice.controller;

import intexsoft.practice.dto.DictionaryDto;
import intexsoft.practice.dto.UpdateDictionaryDto;
import intexsoft.practice.service.RoomStatusService;
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
@RequestMapping("/room-statuses")
@RequiredArgsConstructor
public class RoomStatusController {
    private final RoomStatusService roomStatusService;

    @GetMapping
    public ResponseEntity<List<DictionaryDto>> getAllRoomStatuses() {
        return ResponseEntity.ok(roomStatusService.getAllRoomStatuses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DictionaryDto> getRoomStatusById(@PathVariable UUID id) {
        return ResponseEntity.ok(roomStatusService.getRoomStatusById(id));
    }

    @PostMapping
    public ResponseEntity<DictionaryDto> addRoomStatus(@Valid @RequestBody DictionaryDto roomStatusDto) {
        DictionaryDto responseDictionaryDto = roomStatusService.addRoomStatus(roomStatusDto);
        return ResponseEntity.ok(responseDictionaryDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DictionaryDto> updateRoomStatus(@PathVariable UUID id, @Valid @RequestBody UpdateDictionaryDto updateRoomStatusDto) {
        DictionaryDto responseDictionaryDto = roomStatusService.updateRoomStatus(id, updateRoomStatusDto);
        return ResponseEntity.ok(responseDictionaryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoomStatus(@PathVariable("id") UUID id) {
        roomStatusService.deleteRoomStatusById(id);
        return ResponseEntity.ok("RoomStatus deleted successfully");
    }
}
