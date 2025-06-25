package intexsoft.practice.service;

import intexsoft.practice.dto.AddAmenityDto;
import intexsoft.practice.dto.ResponseAmenityDto;
import intexsoft.practice.dto.UpdateAmenityDto;

import java.util.List;
import java.util.UUID;

public interface AmenityService {
    ResponseAmenityDto addAmenity(AddAmenityDto addAmenityDto);
    List<ResponseAmenityDto> getAllAmenities();
    ResponseAmenityDto getAmenityById(UUID id);
    ResponseAmenityDto updateAmenity(UUID id, UpdateAmenityDto updateAmenityDto);
    void deleteAmenityById(UUID id);
}
