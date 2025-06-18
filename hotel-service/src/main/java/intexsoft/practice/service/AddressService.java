package intexsoft.practice.service;

import intexsoft.practice.dto.AddressDto;
import intexsoft.practice.dto.UpdateAddressDto;

import java.util.List;
import java.util.UUID;

public interface AddressService {
    void addAddress(AddressDto addressDto);
    List<AddressDto> getAllAddresses();
    AddressDto getAddressById(UUID id);
    void updateAddress(UUID id, UpdateAddressDto updateAddressDto);
    void deleteAddressById(UUID id);
}
