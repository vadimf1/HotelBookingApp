package intexsoft.practice.controller;

import intexsoft.practice.dto.DictionaryDto;
import intexsoft.practice.dto.UpdateDictionaryDto;
import intexsoft.practice.service.AmenityStatusService;
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
@RequestMapping("/amenity-statuses")
@RequiredArgsConstructor
public class AmenityStatusController {
    private final AmenityStatusService amenityStatusService;

    @GetMapping
    public ResponseEntity<List<DictionaryDto>> getAllAmenityStatuses() {
        return ResponseEntity.ok(amenityStatusService.getAllAmenityStatuses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DictionaryDto> getAmenityStatusById(@PathVariable UUID id) {
        return ResponseEntity.ok(amenityStatusService.getAmenityStatusById(id));
    }

    @PostMapping
    public ResponseEntity<DictionaryDto> addAmenityStatus(@Valid @RequestBody DictionaryDto amenityStatusDto) {
        DictionaryDto responseDictionaryDto = amenityStatusService.addAmenityStatus(amenityStatusDto);
        return ResponseEntity.ok(responseDictionaryDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DictionaryDto> updateAmenityStatus(@PathVariable UUID id, @Valid @RequestBody UpdateDictionaryDto updateAmenityStatusDto) {
        DictionaryDto responseDictionaryDto = amenityStatusService.updateAmenityStatus(id, updateAmenityStatusDto);
        return ResponseEntity.ok(responseDictionaryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAmenityStatus(@PathVariable("id") UUID id) {
        amenityStatusService.deleteAmenityStatusById(id);
        return ResponseEntity.ok("AmenityStatus deleted successfully");
    }
}