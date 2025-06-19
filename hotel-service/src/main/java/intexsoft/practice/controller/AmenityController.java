package intexsoft.practice.controller;

import intexsoft.practice.dto.AddAmenityDto;
import intexsoft.practice.dto.ResponseAmenityDto;
import intexsoft.practice.dto.UpdateAmenityDto;
import intexsoft.practice.service.AmenityService;
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
@RequestMapping("/amenities")
@RequiredArgsConstructor
public class AmenityController {
    private final AmenityService amenityService;

    @GetMapping
    public ResponseEntity<List<ResponseAmenityDto>> getAllAmenities() {
        return ResponseEntity.ok(amenityService.getAllAmenities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAmenityDto> getAmenityById(@PathVariable UUID id) {
        return ResponseEntity.ok(amenityService.getAmenityById(id));
    }

    @PostMapping
    public ResponseEntity<ResponseAmenityDto> addAmenity(@Valid @RequestBody AddAmenityDto addAmenityDto) {
        ResponseAmenityDto responseAmenityDto = amenityService.addAmenity(addAmenityDto);
        return ResponseEntity.ok(responseAmenityDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseAmenityDto> updateAmenity(@PathVariable UUID id, @Valid @RequestBody UpdateAmenityDto updateAmenityDto) {
        ResponseAmenityDto responseAmenityDto = amenityService.updateAmenity(id, updateAmenityDto);
        return ResponseEntity.ok(responseAmenityDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAmenity(@PathVariable("id") UUID id) {
        amenityService.deleteAmenityById(id);
        return ResponseEntity.ok("Amenity deleted successfully");
    }
}
