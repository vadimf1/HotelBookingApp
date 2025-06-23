package intexsoft.practice.notification_service.service.impl;

import intexsoft.practice.notification_service.config.client.UserClientProperties;
import intexsoft.practice.notification_service.dto.RoomDto;
import intexsoft.practice.notification_service.dto.UserDto;
import intexsoft.practice.notification_service.exception.roomClient.RoomServiceException;
import intexsoft.practice.notification_service.exception.userClient.UserNetworkException;
import intexsoft.practice.notification_service.exception.userClient.UserServerException;
import intexsoft.practice.notification_service.service.UserClientService;
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
public class UserClientServiceImpl implements UserClientService {

    private final RestTemplate restTemplate;
    private final UserClientProperties userClientProperties;

    @Retryable(
            value = {HttpServerErrorException.class, ResourceAccessException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000))
    @Override
    public UserDto getUserById(UUID userId) {
        String url = userClientProperties.getApi() + "/" + userId;
        return restTemplate.getForObject(url, UserDto.class);
    }

    @Recover
    public RoomDto recover(HttpServerErrorException e, UUID userId) {
        log.error("Failed to get user by id {} after retries", userId, e);
        throw new UserServerException("Failed to get user by id " + userId, e);
    }

    @Recover
    public RoomDto recover(ResourceAccessException e, UUID userId) {
        log.error("Network error while getting user by id {} after retries", userId, e);
        throw new UserNetworkException("Network error while getting user by id " + userId, e);
    }
}
