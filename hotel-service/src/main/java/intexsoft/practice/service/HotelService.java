package intexsoft.practice.service;

import intexsoft.practice.dto.AddHotelDto;
import intexsoft.practice.dto.ResponseHotelDto;
import intexsoft.practice.dto.UpdateHotelDto;

import java.util.List;
import java.util.UUID;

public interface HotelService {
    void addHotel(AddHotelDto addHotelDto);
    List<ResponseHotelDto> getAllHotels();
    ResponseHotelDto getHotelById(UUID id);
    void updateHotel(UUID id, UpdateHotelDto updateHotelDto);
    void deleteHotelById(UUID id);
}
