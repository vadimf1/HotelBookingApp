package intexsoft.practice.controller;

import intexsoft.practice.dto.AddHotelDto;
import intexsoft.practice.dto.ResponseHotelDto;
import intexsoft.practice.dto.UpdateHotelDto;
import intexsoft.practice.service.HotelService;
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
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @GetMapping
    public ResponseEntity<List<ResponseHotelDto>> getAllHotels() {
        return ResponseEntity.ok(hotelService.getAllHotels());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseHotelDto> getHotelById(@PathVariable UUID id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    @PostMapping
    public ResponseEntity<ResponseHotelDto> addHotel(@Valid @RequestBody AddHotelDto addHotelDto) {
        ResponseHotelDto responseHotelDto = hotelService.addHotel(addHotelDto);
        return ResponseEntity.ok(responseHotelDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseHotelDto> updateHotel(@PathVariable UUID id, @Valid @RequestBody UpdateHotelDto updateHotelDto) {
        ResponseHotelDto responseHotelDto = hotelService.updateHotel(id, updateHotelDto);
        return ResponseEntity.ok(responseHotelDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable("id") UUID id) {
        hotelService.deleteHotelById(id);
        return ResponseEntity.ok("Hotel deleted successfully");
    }
}

