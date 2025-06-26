package intexsoft.practice.notification_service.service.client;

import intexsoft.practice.notification_service.dto.RoomDto;

import java.util.UUID;

public interface RoomClientService {
    RoomDto getRoomById(UUID roomId);
}
