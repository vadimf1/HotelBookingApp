package intexsoft.practice.notification_service.service.impl;

import intexsoft.practice.notification_service.config.client.UserClientProperties;
import intexsoft.practice.notification_service.dto.RoomDto;
import intexsoft.practice.notification_service.service.RoomClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Profile("!dev")
public class RoomClientServiceImpl implements RoomClientService {

    private final RestTemplate restTemplate;
    private final UserClientProperties roomClientProperties;

    @Override
    public RoomDto getRoomById(UUID roomId) {
        String url = roomClientProperties.getApi() + "/" + roomId;
        return restTemplate.getForObject(url, RoomDto.class);
    }
}
