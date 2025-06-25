package intexsoft.practice.notification_service.mapper;

import intexsoft.practice.dto.notification.BookingCreatedNotification;
import intexsoft.practice.notification_service.entity.BookingNotification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingNotificationMapper {

    @Mapping(source = "bookingId", target = "id")
    BookingNotification toEntity(BookingCreatedNotification bookingCreatedNotification);
}
