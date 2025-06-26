package intexsoft.practice.controller;

import intexsoft.practice.dto.AddressDto;
import intexsoft.practice.dto.UpdateAddressDto;
import intexsoft.practice.service.AddressService;
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
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping
    public ResponseEntity<List<AddressDto>> getAllAddresses() {
        return ResponseEntity.ok(addressService.getAllAddresses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable UUID id) {
        return ResponseEntity.ok(addressService.getAddressById(id));
    }

    @PostMapping
    public ResponseEntity<AddressDto> addAddress(@Valid @RequestBody AddressDto addressDto) {
        AddressDto responseAddressDto = addressService.addAddress(addressDto);
        return ResponseEntity.ok(responseAddressDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable UUID id, @Valid @RequestBody UpdateAddressDto updateAddressDto) {
        AddressDto responseAddressDto = addressService.updateAddress(id, updateAddressDto);
        return ResponseEntity.ok(responseAddressDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable("id") UUID id) {
        addressService.deleteAddressById(id);
        return ResponseEntity.ok("Address deleted successfully");
    }
}
