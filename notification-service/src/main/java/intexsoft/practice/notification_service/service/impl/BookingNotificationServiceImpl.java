package intexsoft.practice.notification_service.service.impl;

import intexsoft.practice.dto.notification.BookingCreatedNotification;
import intexsoft.practice.notification_service.dto.RoomDto;
import intexsoft.practice.notification_service.dto.UserDto;
import intexsoft.practice.notification_service.entity.BookingNotification;
import intexsoft.practice.notification_service.localization.BookingNotificationMessageKeys;
import intexsoft.practice.notification_service.mapper.BookingNotificationMapper;
import intexsoft.practice.notification_service.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingNotificationServiceImpl implements NotificationService<BookingCreatedNotification> {

    private static final String BOOKING_TEMPLATE = "booking-notification.ftl";
    private final MailSenderService mailSenderService;
    private final FreeMarkerMailContentBuilder contentBuilder;
    private final RoomClientService roomClientService;
    private final UserClientService userClientService;
    private final LocaleMappingService localeMappingService;
    private final LocalizedMessageService localizedMessageService;
    private final BookingNotificationDataService bookingNotificationDataService;
    private final BookingNotificationMapper bookingNotificationMapper;

    @Override
    public void notify(BookingCreatedNotification dto) {
        UserDto userDto = userClientService.getUserById(dto.userId());
        RoomDto roomDto = roomClientService.getRoomById(dto.roomId());
        String email = userDto.getEmail();

        Map<String, Object> model = buildModel(dto, roomDto);
        Locale locale = localeMappingService.getLocaleForCountry(
                roomDto.getHotel().getAddress().getCountryCode()
        );
        Map<String, String> msg = localizedMessageService.getBulk(BookingNotificationMessageKeys.ALL_KEYS, locale);

        model.put("msg", msg);
        String subject = msg.get(BookingNotificationMessageKeys.SUBJECT);
        String body = contentBuilder.build(BOOKING_TEMPLATE, model);

        BookingNotification bookingNotification = bookingNotificationMapper.toEntity(dto);
        bookingNotificationDataService.saveBookingNotification(bookingNotification);
        mailSenderService.sendEmail(email, subject, body);

        log.info("Уведомление отправлено на почту {}", email);
    }

    private Map<String, Object> buildModel(BookingCreatedNotification dto, RoomDto roomDto) {
        Map<String, Object> model = new HashMap<>();
        model.put("bookingId", dto.bookingId());
        model.put("checkInDate", dto.checkInDate());
        model.put("checkOutDate", dto.checkOutDate());

        model.put("roomNumber", roomDto.getRoomNumber());
        model.put("roomTypeName", roomDto.getRoomType().getName());
        model.put("roomDescription", roomDto.getDescription());
        model.put("pricePerDay", roomDto.getPricePerDay());

        RoomDto.HotelDto hotel = roomDto.getHotel();
        model.put("hotelName", hotel.getName());
        model.put("hotelEmail", hotel.getEmail());
        model.put("hotelPhone", hotel.getPhoneNumber());
        model.put("hotelWebsite", hotel.getWebsite());

        RoomDto.AddressDto addr = hotel.getAddress();
        String hotelAddress = String.format("%s, %s, %s, %s, %s",
                addr.getStreet(), addr.getCity(), addr.getState(),
                addr.getCountry(), addr.getPostalCode());

        model.put("hotelAddress", hotelAddress);

        return model;
    }
}
