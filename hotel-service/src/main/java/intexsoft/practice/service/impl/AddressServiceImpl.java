package intexsoft.practice.service.impl;

import intexsoft.practice.dto.AddressDto;
import intexsoft.practice.dto.UpdateAddressDto;
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

    public void updateAddress(UUID id, UpdateAddressDto updateAddressDto) {
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ServiceException(AddressExceptionCode.ADDRESS_NOT_FOUNT_BY_ID.getMessage() + id));

        addressMapper.updateEntityFromDto(updateAddressDto, address);
        addressRepository.save(address);
    }

    public void deleteAddressById(UUID id) {
        addressRepository.deleteById(id);
    }
}
