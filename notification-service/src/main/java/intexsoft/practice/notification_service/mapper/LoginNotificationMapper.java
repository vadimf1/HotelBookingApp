package intexsoft.practice.notification_service.mapper;

import intexsoft.practice.dto.notification.AccountLoginNotification;
import intexsoft.practice.notification_service.entity.LoginNotification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginNotificationMapper {
    LoginNotification toEntity(AccountLoginNotification accountLoginNotification);
}
