package intexsoft.practice.notification_service.service;

import intexsoft.practice.notification_service.dto.UserDto;

import java.util.UUID;

public interface UserClientService {
    UserDto getUserById(UUID userId);
}
