package intexsoft.practice.notification_service.stub;

import intexsoft.practice.notification_service.dto.RoomDto;

public class RoomDtoTestFactory {
    public static RoomDto createStubRoomDto() {
        RoomDto room = new RoomDto();
        room.setRoomNumber(101);
        room.setPricePerDay(120.5);
        room.setFloor(2);
        room.setDescription("Stub description");

        RoomDto.RoomTypeDto type = new RoomDto.RoomTypeDto();
        type.setCode("STD");
        type.setName("Standard");
        room.setRoomType(type);

        RoomDto.HotelDto hotel = new RoomDto.HotelDto();
        hotel.setName("Test Hotel");
        hotel.setEmail("hotel@test.com");
        hotel.setPhoneNumber("+123456789");
        hotel.setWebsite("https://test.com");

        RoomDto.AddressDto address = new RoomDto.AddressDto();
        address.setCountry("Testland");
        address.setState("Teststate");
        address.setCity("Testcity");
        address.setStreet("123 Test Street");
        address.setPostalCode("00000");
        address.setCountryCode("MX");

        hotel.setAddress(address);
        room.setHotel(hotel);

        return room;
    }
}

