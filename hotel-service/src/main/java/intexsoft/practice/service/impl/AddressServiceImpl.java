package intexsoft.practice.service.impl;

import intexsoft.practice.dto.AddressDto;
import intexsoft.practice.exception.ServiceException;
import intexsoft.practice.mapper.AddressMapper;
import intexsoft.practice.model.Address;
import intexsoft.practice.repository.AddressRepository;
import intexsoft.practice.service.AddressService;
import intexsoft.practice.util.exceptionCode.AddressExceptionCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public void addAddress(AddressDto addressDto) {
        if (addressDto.getId() != null) {
            throw new ServiceException(AddressExceptionCode.ID_FIELD_EXPECTED_NULL.getMessage());
        }
        addressRepository.save(addressMapper.toEntity(addressDto));
    }

    public List<AddressDto> getAllAddresses() {
        return addressRepository.findAll()
                .stream()
                .map(addressMapper::toDto)
                .toList();
    }

    public AddressDto getAddressById(UUID id) {
        return addressRepository.findById(id)
                .map(addressMapper::toDto)
                .orElseThrow(() -> new ServiceException(AddressExceptionCode.ADDRESS_NOT_FOUNT_BY_ID.getMessage() + id));
    }

    public void updateAddress(AddressDto addressDto) {
        if (addressDto.getId() == null) {
            throw new ServiceException(AddressExceptionCode.ID_FIELD_IS_NULL.getMessage());
        }
        Address address = addressRepository.findById(addressDto.getId())
                .orElseThrow(() -> new ServiceException(AddressExceptionCode.ADDRESS_NOT_FOUNT_BY_ID.getMessage() + addressDto.getId()));

        Address updatedAddress = Address.builder()
                .id(addressDto.getId())
                .country(addressDto.getCountry() != null ? addressDto.getCountry() : address.getCountry())
                .state(addressDto.getState() != null ? addressDto.getState() : address.getState())
                .city(addressDto.getCity() != null ? addressDto.getCity() : address.getCity())
                .street(addressDto.getStreet() != null ? addressDto.getStreet() : address.getStreet())
                .postalCode(addressDto.getPostalCode() != null ? addressDto.getPostalCode() : address.getPostalCode())
                .build();

        addressRepository.save(updatedAddress);
    }

    public void deleteAddressById(UUID id) {
        addressRepository.deleteById(id);
    }
}
