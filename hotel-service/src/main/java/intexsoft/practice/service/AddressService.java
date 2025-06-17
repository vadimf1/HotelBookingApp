package intexsoft.practice.service;

import intexsoft.practice.dto.AddressDto;

import java.util.List;
import java.util.UUID;

public interface AddressService {
    void addAddress(AddressDto addressDto);
    List<AddressDto> getAllAddresses();
    AddressDto getAddressById(UUID id);
    void updateAddress(AddressDto addressDto);
    void deleteAddressById(UUID id);
}
