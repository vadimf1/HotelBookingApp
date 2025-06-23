package intexsoft.practice.notification_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomDto {
    private int roomNumber;
    private double pricePerDay;
    private int floor;
    private String description;
    private RoomTypeDto roomType;
    private HotelDto hotel;

    @Getter
    @Setter
    public static class RoomTypeDto {
        private String code;
        private String name;
    }

    @Getter
    @Setter
    public static class HotelDto {
        private String name;
        private String email;
        private String phoneNumber;
        private String website;
        private AddressDto address;
    }

    @Getter
    @Setter
    public static class AddressDto {
        private String country;
        private String state;
        private String city;
        private String street;
        private String postalCode;
        private String countryCode;
    }
}

