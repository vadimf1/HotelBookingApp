package intexsoft.practice.notification_service.service.client;

import intexsoft.practice.notification_service.config.client.UserClientProperties;
import intexsoft.practice.notification_service.dto.RoomDto;
import intexsoft.practice.notification_service.exception.roomClient.RoomNetworkException;
import intexsoft.practice.notification_service.exception.roomClient.RoomServerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Profile("!dev")
@Slf4j
public class RoomClientServiceImpl implements RoomClientService {

    private final RestTemplate restTemplate;
    private final UserClientProperties roomClientProperties;

    @Retryable(
            value = {HttpServerErrorException.class, ResourceAccessException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    @Override
    public RoomDto getRoomById(UUID roomId) {
        String url = roomClientProperties.getApi() + "/" + roomId;
        return restTemplate.getForObject(url, RoomDto.class);
    }

    @Recover
    public RoomDto recover(HttpServerErrorException e, UUID roomId) {
        log.error("Failed to get room by id {} after retries", roomId, e);
        throw new RoomServerException("Failed to get room by id " + roomId, e);
    }

    @Recover
    public RoomDto recover(ResourceAccessException e, UUID roomId) {
        log.error("Network error while getting room by id {} after retries", roomId, e);
        throw new RoomNetworkException("Network error while getting room by id " + roomId, e);
    }
}
