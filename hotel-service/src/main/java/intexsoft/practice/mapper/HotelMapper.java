package intexsoft.practice.mapper;

import intexsoft.practice.dto.AddHotelDto;
import intexsoft.practice.dto.ResponseHotelDto;
import intexsoft.practice.dto.UpdateHotelDto;
import intexsoft.practice.model.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {HotelStatusMapper.class})
public interface HotelMapper {
    ResponseHotelDto toDto(Hotel hotel);
    Hotel toEntity(AddHotelDto addHotelDto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(UpdateHotelDto updateHotelDto, @MappingTarget Hotel hotel);
}
