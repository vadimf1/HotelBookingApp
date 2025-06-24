package intexsoft.practice.notification_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import intexsoft.practice.notification_service.dto.RoomDto;
import intexsoft.practice.notification_service.dto.UserDto;

import java.math.BigDecimal;

public class StubDataFactory {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String createRoomJson() {
        try {
            return mapper.writeValueAsString(createRoomDto());
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize RoomDto", e);
        }
    }

    public static RoomDto createRoomDto() {
        RoomDto room = new RoomDto();
        room.setRoomNumber(101);
        room.setPricePerDay(150.0);
        room.setFloor(3);
        room.setDescription("Spacious deluxe room");

        RoomDto.RoomTypeDto roomType = new RoomDto.RoomTypeDto();
        roomType.setCode("DELUXE");
        roomType.setName("Deluxe Room");
        room.setRoomType(roomType);

        RoomDto.HotelDto hotel = new RoomDto.HotelDto();
        hotel.setName("Grand Hotel");
        hotel.setEmail("contact@grandhotel.com");
        hotel.setPhoneNumber("+1234567890");
        hotel.setWebsite("https://grandhotel.com");

        RoomDto.AddressDto address = new RoomDto.AddressDto();
        address.setCountry("USA");
        address.setState("California");
        address.setCity("Los Angeles");
        address.setStreet("Sunset Boulevard 123");
        address.setPostalCode("90001");
        address.setCountryCode("US");

        hotel.setAddress(address);
        room.setHotel(hotel);

        return room;
    }

    public static UserDto createUserDto() {
        UserDto user = new UserDto();
        user.setEmail("mocked.user@example.com");
        return user;
    }
}