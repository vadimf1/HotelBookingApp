package intexsoft.practice.notification_service.stub;

import intexsoft.practice.notification_service.dto.RoomDto;
import intexsoft.practice.notification_service.service.client.RoomClientService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Profile("dev")
@Service
public class StubRoomClientService implements RoomClientService {
    @Override
    public RoomDto getRoomById(UUID roomId) {
        return RoomDtoTestFactory.createStubRoomDto();
    }
}
