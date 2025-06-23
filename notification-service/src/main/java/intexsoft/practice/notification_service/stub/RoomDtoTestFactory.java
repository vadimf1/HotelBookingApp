package intexsoft.practice.notification_service.stub;

import intexsoft.practice.notification_service.dto.RoomDto;

public class RoomDtoTestFactory {
    public static RoomDto createStubRoomDto() {
        RoomDto room = new RoomDto();
        room.setRoomNumber(StubRoomConstants.ROOM_NUMBER);
        room.setPricePerDay(StubRoomConstants.PRICE_PER_DAY);
        room.setFloor(StubRoomConstants.FLOOR);
        room.setDescription(StubRoomConstants.ROOM_DESCRIPTION);

        RoomDto.RoomTypeDto type = new RoomDto.RoomTypeDto();
        type.setCode(StubRoomConstants.ROOM_TYPE_CODE);
        type.setName(StubRoomConstants.ROOM_TYPE_NAME);
        room.setRoomType(type);

        RoomDto.HotelDto hotel = new RoomDto.HotelDto();
        hotel.setName(StubRoomConstants.HOTEL_NAME);
        hotel.setEmail(StubRoomConstants.HOTEL_EMAIL);
        hotel.setPhoneNumber(StubRoomConstants.HOTEL_PHONE);
        hotel.setWebsite(StubRoomConstants.HOTEL_WEBSITE);

        RoomDto.AddressDto address = new RoomDto.AddressDto();
        address.setCountry(StubRoomConstants.ADDRESS_COUNTRY);
        address.setState(StubRoomConstants.ADDRESS_STATE);
        address.setCity(StubRoomConstants.ADDRESS_CITY);
        address.setStreet(StubRoomConstants.ADDRESS_STREET);
        address.setPostalCode(StubRoomConstants.ADDRESS_POSTAL_CODE);
        address.setCountryCode(StubRoomConstants.ADDRESS_COUNTRY_CODE);

        hotel.setAddress(address);
        room.setHotel(hotel);

        return room;
    }
}

