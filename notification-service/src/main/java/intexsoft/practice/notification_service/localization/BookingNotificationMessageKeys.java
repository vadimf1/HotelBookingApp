package intexsoft.practice.notification_service.localization;

import java.util.List;

public final class BookingNotificationMessageKeys {
    public static final String SUBJECT = "booking.subject";
    public static final String GREETING = "booking.greeting";
    public static final String THANK_YOU = "booking.thank_you";
    public static final String DETAILS_INTRO = "booking.details_intro";
    public static final String INFO_TITLE = "booking.info_title";
    public static final String BOOKING_ID = "booking.id";
    public static final String DATES = "booking.dates";
    public static final String ROOM_NUMBER = "booking.room_number";
    public static final String ROOM_TYPE_NAME = "booking.room_type";
    public static final String ROOM_DESCRIPTION = "booking.description";
    public static final String PRICE_PER_DAY = "booking.price";
    public static final String HOTEL_INFO_TITLE = "hotel.info_title";
    public static final String HOTEL_NAME = "hotel.name";
    public static final String HOTEL_EMAIL = "hotel.email";
    public static final String HOTEL_PHONE = "hotel.phone";
    public static final String HOTEL_WEBSITE = "hotel.website";
    public static final String HOTEL_ADDRESS = "hotel.address";
    public static final String CONTACT_NOTE = "booking.contact_note";
    public static final String FAREWELL = "booking.farewell";
    public static final String SIGNATURE = "booking.signature";

    public static final List<String> ALL_KEYS = List.of(
            SUBJECT,
            GREETING,
            THANK_YOU,
            DETAILS_INTRO,
            INFO_TITLE,
            BOOKING_ID,
            DATES,
            ROOM_NUMBER,
            ROOM_TYPE_NAME,
            ROOM_DESCRIPTION,
            PRICE_PER_DAY,
            HOTEL_INFO_TITLE,
            HOTEL_NAME,
            HOTEL_EMAIL,
            HOTEL_PHONE,
            HOTEL_WEBSITE,
            HOTEL_ADDRESS,
            CONTACT_NOTE,
            FAREWELL,
            SIGNATURE
    );

    private BookingNotificationMessageKeys() {
    }
}
