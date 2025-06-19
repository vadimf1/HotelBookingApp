package intexsoft.practice.controller;

import intexsoft.practice.dto.DictionaryDto;
import intexsoft.practice.dto.UpdateDictionaryDto;
import intexsoft.practice.service.HotelStatusService;
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
@RequestMapping("/hotel-statuses")
@RequiredArgsConstructor
public class HotelStatusController {
    private final HotelStatusService hotelStatusService;

    @GetMapping
    public ResponseEntity<List<DictionaryDto>> getAllHotelStatuses() {
        return ResponseEntity.ok(hotelStatusService.getAllHotelStatuses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DictionaryDto> getHotelStatusById(@PathVariable UUID id) {
        return ResponseEntity.ok(hotelStatusService.getHotelStatusById(id));
    }

    @PostMapping
    public ResponseEntity<DictionaryDto> addHotelStatus(@Valid @RequestBody DictionaryDto hotelStatusDto) {
        DictionaryDto responseDictionaryDto = hotelStatusService.addHotelStatus(hotelStatusDto);
        return ResponseEntity.ok(responseDictionaryDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DictionaryDto> updateHotelStatus(@PathVariable UUID id, @Valid @RequestBody UpdateDictionaryDto updateHotelStatusDto) {
        DictionaryDto responseDictionaryDto = hotelStatusService.updateHotelStatus(id, updateHotelStatusDto);
        return ResponseEntity.ok(responseDictionaryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHotelStatus(@PathVariable("id") UUID id) {
        hotelStatusService.deleteHotelStatusById(id);
        return ResponseEntity.ok("HotelStatus deleted successfully");
    }
}
